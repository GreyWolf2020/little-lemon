package com.example.littlelemon.presentation.menudescription

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.littlelemon.presentation.common.MyTopAppBar
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.presentation.home.navigateToHome
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.presentation.util.viewModelFactory
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

private const val dishTitleArg = "dishTitle"
private const val MenuDescriptionRoute = "menuDescription"

internal class MenuDescriptionArg(val dishId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle[dishTitleArg]) as String)
}

/* in viewModel
internal class ConversationViewModel(...,
                                     savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val conversationArgs = ConversationArgs(savedStateHandle)
}*/
fun NavGraphBuilder.menuDescriptionScreen(
    navController: NavHostController
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
                    backStackEntry.arguments?.getString(dishTitleArg) ?: ""
                )
            }
        )


        MenuDescriptionScreen(
            navigateToHome = navController::navigateToHome,
            navigateToProfile = navController::navigateToProfile,
            viewModel.dish.collectAsState().value,
            viewModel.topings.collectAsState(listOf()).value,
            viewModel::onTopingSelected
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
    dish: Dish,
    topings: List<Toping>,
    onTopingSelected: (Toping) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MyTopAppBar(
                navigateToHome = navigateToHome,
                onclickProfile = navigateToProfile,
                onclickMenu = {}
            )
        }

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
                            numOfDishes = dish.dishCnt.toString(),
                            dishPrice = dish.price,
                            onIncNumOfDish = { dish.incrementDishCnt() },
                            onDecNumOfDish = { dish.decrementDishCount() },
                            onAddToBasket = { /*TODO*/ },
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
        dish = Dish(
            name = "Bruschetta",
            description = "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil. Topped with chopped tomatoes, oregano and fresh bazil.",
            price = "10.99",
            imageUrl = "",
            category = "Starter"
        ),
        topings = listOf(
            Dressing(),
            Pamersan(),
            Feta()
        ),
        onTopingSelected = {}
    )
}