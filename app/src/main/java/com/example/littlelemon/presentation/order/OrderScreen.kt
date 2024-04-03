package com.example.littlelemon.presentation.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.littlelemon.presentation.util.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.littlelemon.MyApp
import com.example.littlelemon.R
import com.example.littlelemon.presentation.common.DeliveryOptions
import com.example.littlelemon.presentation.common.LilyLemonFilledButton
import com.example.littlelemon.presentation.common.MyTopAppBar
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.presentation.home.navigateToHome
import com.example.littlelemon.presentation.menudescription.navigateToMenuDescription
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

private const val OrderRoute = "order"
fun NavGraphBuilder.orderScreen(
    navController: NavHostController,
    onClickMenu: () -> Unit
) {
    composable(
        OrderRoute
    ) {
        val viewModel = viewModel<OrderViewModel>(
            factory = viewModelFactory {
                OrderViewModel(
                    MyApp.appModule.menuRepository,
                    MyApp.appModule.userOrderRepo
                )
            }
        )
        OrderScreen(
            navigateToHome = navController::navigateToHome,
            navigateToMenuDesc = navController::navigateToMenuDescription,
            navigateToProfile = navController::navigateToProfile,
            dishes = viewModel.dishes.collectAsState().value,
            orders = viewModel.orders.collectAsState().value,
            serviceCost = viewModel.serviceCost.collectAsState().value,
            deliveryCost = viewModel.deliveryCost.collectAsState().value,
            addCutlery = viewModel.addCutlery.collectAsState().value,
            onCutleryClicked = viewModel::toggleAddCutlery,
            onClickMenu = onClickMenu,
            isOrdering = viewModel.isOrdering.collectAsState().value,
            onClickCheckout = viewModel::onCheckout,
            onDoneCheckout = {
                viewModel.onDoneCheckout()
                navController.navigateToHome()
            }

        )
    }
}

fun NavController.navigateToOrder() {
    if (this.currentBackStackEntry?.destination?.route != OrderRoute)
        this.navigate("$OrderRoute")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OrderScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
    onCutleryClicked: () -> Unit = {},
    onClickMenu: () -> Unit = {},
    orders: List<Order> = emptyList<Order>(),
    deliveryCost: Double = 0.0,
    serviceCost: Double = 0.0,
    dishes: List<Dish> = emptyList<Dish>(),
    navigateToMenuDesc: (String) -> Unit = {  },
    addCutlery: Boolean = false,
    dismissSnackBar: () -> Unit = {  },
    isOrdering: Boolean = false,
    onClickCheckout: () -> Unit = {  },
    onDoneCheckout: () -> Unit = {  }
) {
    val context = LocalContext.current
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(key1 = isOrdering) {
        if (isOrdering) {
            val result = snackBarHostState
                .showSnackbar(
                    message = "Ordering",
                    actionLabel = "Make Payment",
                    duration = SnackbarDuration.Indefinite
                )
            when (result) {
                Dismissed -> {
                    dismissSnackBar()
                    onDoneCheckout()
                }
                ActionPerformed -> {
                    dismissSnackBar()
                    onDoneCheckout()
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
                navigateToHome =  navigateToHome,
                onclickProfile = navigateToProfile,
                onclickMenu = onClickMenu
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.weight(0.055f),
                ) {
                    DeliveryOptions(
                        modifier = Modifier.padding(horizontal = Dimensions.large)
                    )
                    HorizontalDivider()
                }
                CutleryOffer(
                    modifier = Modifier.weight(0.08f),
                    onClickGetCutlery = onCutleryClicked,
                    isCutlerySelected = addCutlery
                )
                OrderSummary(
                    modifier = Modifier.weight(0.2f),
                    orders = orders
                )
                OrderMoreAd(
                    modifier = Modifier.weight(0.14f),
                    dishes = dishes,
                    onDishClicked = navigateToMenuDesc
                )
                Spacer(
                    modifier = Modifier
                        .weight(0.025f)
                )
                OrderTotality(
                    modifier = Modifier.weight(0.4f),
                    listOfOrders = orders,
                    deliveryCost = deliveryCost,
                    serviceCost = serviceCost,
                )
                LilyLemonFilledButton(
                    modifier = Modifier.padding(horizontal = Dimensions.large, vertical = Dimensions.xLarge),
                    onClick = onClickCheckout,
                    buttonText = stringResource(R.string.checkout)
                )
            }
        }
    }
}

@Preview
@Composable
fun OrderScreenPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    OrderScreen(
        navigateToHome = { },
        navigateToProfile = { }
    )
}