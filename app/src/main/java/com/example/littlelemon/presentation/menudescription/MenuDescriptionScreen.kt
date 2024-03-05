package com.example.littlelemon.presentation.menudescription

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.littlelemon.MyApp
import com.example.littlelemon.R
import com.example.littlelemon.presentation.common.BasketFab
import com.example.littlelemon.presentation.common.DeliveryOptions
import com.example.littlelemon.presentation.common.MyTopAppBar
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.presentation.home.navigateToHome
import com.example.littlelemon.presentation.order.navigateToOrder
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.presentation.util.viewModelFactory
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

private const val TAG = "MenuDescriptionScreen"
private const val dishTitleArg = "dishTitle"
private const val MenuDescriptionRoute = "menuDescription"


internal class MenuDescriptionArg(val dishId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle[dishTitleArg]) as String)
}

fun NavGraphBuilder.menuDescriptionScreen(
    navController: NavHostController,
    onClickMenu: () -> Unit
) {
    composable(
        "$MenuDescriptionRoute/{$dishTitleArg}",
        arguments = listOf(
            navArgument(dishTitleArg) {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { backStackEntry ->
        val viewModel = viewModel<MenuDescriptionViewModel>(
            factory = viewModelFactory {
                MenuDescriptionViewModel(
                    MyApp.appModule.menuRepository,
                    MyApp.appModule.userOrderRepo,
                    backStackEntry.arguments?.getString(dishTitleArg) ?: ""
                )
            }
        )
        MenuDescriptionScreen(
            navigateToHome = navController::navigateToHome,
            navigateToProfile = navController::navigateToProfile,
            navigateToOrder = navController::navigateToOrder,
            dish = viewModel.dish.collectAsState().value,
            isBasketEmpty = viewModel.isUserOrderEmpty.collectAsState().value,
            topings = viewModel.topings.collectAsState(listOf()).value,
            onTopingSelected = viewModel::onTopingSelected,
            onDishInc = viewModel::onDishCntInc,
            onDishDec = viewModel::onDishCntDec,
            onClickMenu = onClickMenu,
            onAddOrder = viewModel::onOrder,
            dismissSnackBar = viewModel::onOrderDoneAck,
            isAddingOrder = viewModel.isAddingOrder.collectAsState().value
        )
    }

}

fun NavController.navigateToMenuDescription(dishTitle: String) {
    this
        .navigate("$MenuDescriptionRoute/$dishTitle")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDescriptionScreen(
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToOrder: () -> Unit,
    dish: Dish,
    topings: List<Toping>,
    onTopingSelected: (Toping) -> Unit,
    isBasketEmpty: Boolean = true,
    onClickMenu: () -> Unit = {  },
    onDishInc: () -> Unit = { },
    onDishDec: () -> Unit = { },
    onAddOrder: () -> Unit = {  },
    dismissSnackBar: () -> Unit = { },
    isAddingOrder: Boolean = false,
) {
    val context = LocalContext.current
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(key1 = isAddingOrder) {
        if (isAddingOrder) {
            val result = snackBarHostState
                .showSnackbar(
                    message = "Ordering",
                    actionLabel = "Done",
                    duration = SnackbarDuration.Indefinite
                )
            when (result) {
                SnackbarResult.Dismissed -> {
                    dismissSnackBar()
                }
                SnackbarResult.ActionPerformed -> {
                    dismissSnackBar()
                }
            }
        }
    }
    Scaffold(
        snackbarHost = {
                       SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            MyTopAppBar(
                navigateToHome = navigateToHome,
                onclickProfile = navigateToProfile,
                onclickMenu = onClickMenu
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            BasketFab(
                isBasketEmpty = isBasketEmpty,
                goToOrder = navigateToOrder
            )
        },

    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data(dish.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.meal_placeholder),
                    alignment = Alignment.Center,
                    contentDescription = "Image of ${dish.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.38f)
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppTheme.dimens.large)
                        .weight(0.48f),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        MenuItemDetails(
                            name = dish.name,
                            description = dish.description
                        )
                        DeliveryOptions(modifier = Modifier.padding(vertical = AppTheme.dimens.medium))
                        Ordering(
                            topings = topings,
                            onTopingSelected = onTopingSelected,
                            numOfDishes = dish.qty.toString(),
                            dishPrice = dish.price.toString(),
                            onIncNumOfDish = onDishInc,
                            onDecNumOfDish = onDishDec,
                            onAddToBasket = onAddOrder,
                            enableAddToBasketButton = true,
                            modifier = Modifier.padding(top = AppTheme.dimens.xxSmall, bottom = AppTheme.dimens.large)
                        )

                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun MenuDescriptionScreenPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    MenuDescriptionScreen(
        navigateToHome = {},
        navigateToProfile = {},
        navigateToOrder = {},
        dish = Dish(
            name = "Bruschetta",
            description = "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil. Topped with chopped tomatoes, oregano and fresh bazil.",
            price = 10.99,
            imageUrl = "",
            category = "Starter",
            qty = 0
        ),
        onDishDec = { },
        onDishInc = { },
        topings = listOf(
            Dressing(),
            Pamersan(),
            Feta()
        ),
        onTopingSelected = {}
    )
}