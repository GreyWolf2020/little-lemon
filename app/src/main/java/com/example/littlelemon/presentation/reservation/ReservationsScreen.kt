package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.littlelemon.presentation.common.MyTopAppBar
import com.example.littlelemon.presentation.home.navigateToHome
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

private const val ReservationRoute = "reservation"
fun NavGraphBuilder.reservationScreen(
    navController: NavHostController
) {
    composable(
        ReservationRoute
    ) {
        val viewModel = viewModel<ReservationViewModel>(
            factory = viewModelFactory {
                ReservationViewModel()
            }
        )
        ReservationScreen(
            navigateToHome = navController::navigateToHome,
            navigateToProfile = navController::navigateToProfile,
            selectedSexn = viewModel.selectedSection.collectAsState().value,
            onSexnSelected = viewModel::onSectionSelected
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
    selectedSexn: ReservationSection,
    onSexnSelected: (ReservationSection) -> Unit,
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
                        .height(293.dp),
                    onBackClicked = {}
                )
                ReservationSection(
                    modifier = Modifier
                        .padding(horizontal = Dimensions.medium, vertical = Dimensions.large),
                    allSections = ReservationSection.allSections,
                    selectedSection = selectedSexn,
                    onSectionSelected = onSexnSelected
                )
                when(selectedSexn) {
                    is PaymentInfo -> {

                    }
                    is ReservationInfo -> {

                    }
                    is ReviewInfo -> {

                    }
                }
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
        navigateToProfile = { },
        onSexnSelected = {},
        selectedSexn = ReservationInfo(),
    )
}
