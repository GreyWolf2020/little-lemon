package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.littlelemon.presentation.common.LilyLemonFilledButton
import com.example.littlelemon.presentation.common.LilyLemonUnFilledButton
import com.example.littlelemon.presentation.common.LittleLemonNavDrawer
import com.example.littlelemon.presentation.common.MyTopAppBar
import com.example.littlelemon.presentation.home.navigateToHome
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.presentation.util.viewModelFactory
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.launch

private const val ReservationRoute = "reservation"
private const val ReservationIndex: Int = 1
fun NavGraphBuilder.reservationScreen(
    navController: NavHostController,
    onClickMenu: () -> Unit
) {
    composable(
        ReservationRoute
    ) {
        val viewModel = viewModel<ReservationViewModel>(
            factory = viewModelFactory {
                ReservationViewModel()
            }
        )
        val gotoHome = viewModel.gotoHome.collectAsState().value

        if (gotoHome)
            navController.navigateToHome()
        ReservationScreen(
            navigateToHome = navController::navigateToHome,
            navigateToProfile = navController::navigateToProfile,
            selectedSexn = viewModel.selectedSection.collectAsState().value,
            onSexnSelected = viewModel::onSectionSelected,
            upPress = navController::navigateUp,
            onClickProceed = viewModel::onClickProceed,
            reviewInfoUi = viewModel.reviewInfoUI(),
            paymentInfoUi = viewModel.paymentInfoUI(),
            reservationInfoUi = viewModel.reservationInfoUI(),
            showSnackBar = viewModel.showSnackBar.collectAsState().value,
            dismissSnackBar = viewModel::dismissSnackBar,
            onClickMenu = onClickMenu
        )

    }
}
fun NavController.navigateToReservation() {
    if (this.currentBackStackEntry?.destination?.route != ReservationRoute)
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
    upPress: () -> Unit,
    onClickProceed: () -> Unit,
    reservationInfoUi: @Composable() (ColumnScope.() -> Unit),
    paymentInfoUi: @Composable() (ColumnScope.() -> Unit),
    reviewInfoUi: @Composable() (ColumnScope.() -> Unit),
    showSnackBar: Boolean = false,
    dismissSnackBar: () -> Unit = {},
    onClickMenu: () -> Unit = {}
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    var gotoNextProcess by remember {
        mutableStateOf("Proceed")
    }
    LaunchedEffect(key1 = showSnackBar ) {
        if (showSnackBar) {
            val result = snackBarHostState
                .showSnackbar(
                    message = "The entered information is not complete. Fill in all the required Info.",
                    actionLabel = "Accept",
                    duration = SnackbarDuration.Indefinite
                )
            when (result) {
                SnackbarResult.Dismissed -> {
                    dismissSnackBar()
                }

                SnackbarResult.ActionPerformed -> {
                    onSexnSelected(ReservationSection.firstSection)
                    dismissSnackBar()
                }
            }
        }

    }
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
        topBar = {
            MyTopAppBar(
                navigateToHome = navigateToHome,
                onclickProfile = navigateToProfile,
                onclickMenu = onClickMenu
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
                    onBackClicked = upPress
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
                        gotoNextProcess = "Proceed"
                        paymentInfoUi()
                    }
                    is ReservationInfo -> {
                        gotoNextProcess = "Proceed"
                        reservationInfoUi()
                    }
                    is ReviewInfo -> {
                        gotoNextProcess = "Submit"
                        reviewInfoUi()
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
                        onClick = upPress,
                        buttonText = "Cancel"
                    )
                    Spacer(modifier = Modifier. weight(0.2f),)
                    LilyLemonFilledButton(
                        modifier = Modifier. weight(0.4f),
                        onClick = onClickProceed,
                        buttonText = gotoNextProcess
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
        selectedSexn = ReviewInfo(),
        onSexnSelected = {},
        navigateToHome = { },
        navigateToProfile = { },
        upPress = { },
        onClickProceed = { },
        reservationInfoUi = {
            ReservationInfoUI(
                modifier = Modifier
                    .padding(horizontal = Dimensions.large)
                    .weight(0.1f)
                    .verticalScroll(rememberScrollState()),
            )
        },
        paymentInfoUi = {
            PaymentInfoUI(
                modifier = Modifier
                    .padding(horizontal = Dimensions.large)
                    .weight(0.1f)
                    .verticalScroll(rememberScrollState()),
            )
        },
        reviewInfoUi = {
            ReviewInfoUI(
                modifier = Modifier
                    .padding(horizontal = Dimensions.large)
                    .weight(0.1f)
                    .verticalScroll(rememberScrollState()),
                customerReserveInfo = Customer(
                    salutation = Mr(),
                    fullName = "James Variety Phiri",
                    attendants = 2,
                    date = null,
                    time = null,
                    payeeInfo = Payee(
                        email = "variety@hotmail.com",
                        phoneNum = "0771 079 854",
                        address = "183 Mbuyanehanda Street",
                        expDate = null,
                        cardNum = "",
                        cvv = ""
                    )
                )
            )
        },
        showSnackBar = false
    )
}


private fun ReservationViewModel.paymentInfoUI(): @Composable ColumnScope.() -> Unit = {
    PaymentInfoUI(
        modifier = Modifier
            .padding(horizontal = Dimensions.large)
            .weight(0.1f)
            .verticalScroll(rememberScrollState()),
        cardNum = cardNum.collectAsState().value,
        onCardNumChange = ::onCardNumChange,
        cvv = cvv.collectAsState().value,
        onCvvChange = ::onCvvChange,
        address = address.collectAsState().value,
        onAddressChange = ::onAddressChange,
        email = email.collectAsState().value,
        onEmailChange = ::onEmailChange,
        expDate = expDate.collectAsState().value,
        onExpDateChange = ::onExpDateChange,
        phoneNum = phoneNum.collectAsState().value,
        onPhoneNumChange = ::onPhoneNumChange
    )
}

private fun ReservationViewModel.reservationInfoUI(): @Composable ColumnScope.() -> Unit = {
    ReservationInfoUI(
        modifier = Modifier
            .padding(horizontal = Dimensions.large)
            .weight(0.1f)
            .verticalScroll(rememberScrollState()),
        honorific = honorific.collectAsState().value,
        onHonorificChange = ::onHonorificChange,
        surname = surname.collectAsState().value,
        onSurnameChange = ::onSurnameChange,
        middleNames = middleNames.collectAsState().value,
        onMiddleNamesChange = ::onMiddleNamesChange,
        firstName = firstName.collectAsState().value,
        onFirstNameChange = ::onFirstNameChange,
        date = date.collectAsState().value,
        onDateChange = ::onDateChange,
        time = time.collectAsState().value,
        onTimeChange = ::onTimeChange,
        attendants = attendants.collectAsState().value,
        incAttendants = ::onAttendantInc,
        decAttendants = ::onAttendantDec
    )
}

private fun ReservationViewModel.reviewInfoUI(): @Composable ColumnScope.() -> Unit = {
    ReviewInfoUI(
        modifier = Modifier
            .padding(horizontal = Dimensions.large)
            .weight(0.1f)
            .verticalScroll(rememberScrollState()),
        customerReserveInfo = customer.collectAsState().value
    )
}