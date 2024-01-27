package com.example.littlelemon.presentation.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.littlelemon.R
import com.example.littlelemon.presentation.common.DeliveryOptions
import com.example.littlelemon.presentation.common.MyButton
import com.example.littlelemon.presentation.common.MyTopAppBar
import com.example.littlelemon.presentation.home.navigateToHome
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

private const val OrderRoute = "order"
fun NavGraphBuilder.orderScreen(
    navController: NavHostController
) {
    composable(
        OrderRoute
    ) { backStackEntry ->
        val viewModel = viewModel<OrderViewModel>(
            factory = viewModelFactory {
                OrderViewModel()
            }
        )
        OrderScreen(
            navigateToHome = navController::navigateToHome,
            navigateToProfile = navController::navigateToProfile
        )
    }
}

fun NavController.navigateToOrderScreen() {
    this.
    navigate("$OrderRoute")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OrderScreen(
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
    onCutleryClicked: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                navigateToHome =  navigateToHome,
                onclickProfile = navigateToProfile,
                onclickMenu = { /*TODO*/ })
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
                    Divider()
                }
                CutleryOffer(
                    modifier = Modifier.weight(0.08f),
                    onClickGetCutlery = onCutleryClicked,
                    isCutlerySelected = false
                )
                OrderSummary(
                    modifier = Modifier.weight(0.2f),
                    orders = orders
                )
                OrderMoreAd(
                    modifier = Modifier.weight(0.14f)
                )
                Spacer(
                    modifier = Modifier
                        .weight(0.025f)
                )
                OrderTotality(
                    modifier = Modifier.weight(0.4f)
                )
                MyButton(
                    modifier = Modifier.padding(horizontal = Dimensions.large, vertical = Dimensions.xLarge),
                    onClick = { /*TODO*/ }
                    , buttonText = stringResource(R.string.checkout)
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
        navigateToProfile = { },

    )
}