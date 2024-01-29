package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.littlelemon.presentation.common.MyTopAppBar
import com.example.littlelemon.presentation.home.navigateToHome
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.ui.theme.LittleLemonTheme

private const val ReservationRoute = "reservation"
fun NavGraphBuilder.reservationScreen(
    navController: NavHostController
) {
    composable(
        ReservationRoute
    ) {
        ReservationScreen(
            navigateToHome = navController::navigateToHome,
            navigateToProfile = navController::navigateToProfile
        )
    }
}
fun NavController.navigateToReservation() {
    this.navigate(ReservationRoute)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReservationScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MyTopAppBar(
                navigateToHome = navigateToHome,
                onclickProfile = navigateToProfile,
                onclickMenu = { /*TODO*/ }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),

            ) {
                BrandingReserveMsg(
                    modifier = Modifier
                        .requiredHeight(293.dp),
                    onBackClicked = {}
                )

            }
        }

    }
}

@Preview
@Composable
fun ReservationScreenPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    ReservationScreen(
        navigateToHome = { },
        navigateToProfile = { }
    )
}
