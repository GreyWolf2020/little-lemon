package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.littlelemon.presentation.common.LilyLemonFilledButton
import com.example.littlelemon.presentation.common.LilyLemonUnFilledButton
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
                        .height(280.dp),
                    onBackClicked = {}
                )
                ReservationSection(
                    modifier = Modifier
                        .padding(horizontal = Dimensions.medium, vertical = Dimensions.medium),
                    allSections = ReservationSection.allSections,
                    selectedSection = selectedSexn,
                    onSectionSelected = onSexnSelected
                )
                when(selectedSexn) {
                    is PaymentInfo -> {
                        PaymentInfoUI(
                            modifier = Modifier
                                .padding(horizontal = Dimensions.large)
                                .weight(0.1f)
                                .verticalScroll(rememberScrollState())
                        )
                    }
                    is ReservationInfo -> {
                        ReservationInfoUI(
                            modifier = Modifier
                                .padding(horizontal = Dimensions.large)
                                .weight(0.1f)
                                .verticalScroll(rememberScrollState())
                        )
                    }
                    is ReviewInfo -> {
                        ReviewInfoUI(
                            modifier = Modifier
                                .padding(horizontal = Dimensions.large)
                                .weight(0.1f)
                                .verticalScroll(rememberScrollState()),
                            customerReserveInfo = CustomerReserveInfo(
                                fullName = "James V. Phiri",
                                address = "183 Mbuyanehanda Street",
                                emailAdd = "variety@hotmail.com",
                                phonNum = "0771 079 854",
                                date = "23 Feb 2024",
                                time = "10 00 Hrs",
                                attendants = 3
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(Dimensions.large))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(horizontal = Dimensions.medium),
                ) {
                    LilyLemonUnFilledButton(
                        modifier = Modifier. weight(0.4f),
                        onClick = {  },
                        buttonText = "Cancel"
                    )
                    Spacer(modifier = Modifier. weight(0.2f),)
                    LilyLemonFilledButton(
                        modifier = Modifier. weight(0.4f),
                        onClick = { },
                        buttonText = "Proceed"
                    )
                }
                Spacer(modifier = Modifier.height(Dimensions.large))
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
        selectedSexn = ReviewInfo(),
    )
}
