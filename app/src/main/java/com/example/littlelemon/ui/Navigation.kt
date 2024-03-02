package com.example.littlelemon.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.littlelemon.data.local.userprofile.UserProfile
import com.example.littlelemon.presentation.common.LittleLemonNavDrawer
import com.example.littlelemon.presentation.home.HomeRoute
import com.example.littlelemon.presentation.home.homeScreen
import com.example.littlelemon.presentation.home.navigateToHome
import com.example.littlelemon.presentation.menudescription.menuDescriptionScreen
import com.example.littlelemon.presentation.onboarding.OnboardingRoute
import com.example.littlelemon.presentation.onboarding.onboardingScreen
import com.example.littlelemon.presentation.order.orderScreen
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.presentation.profile.profileScreen
import com.example.littlelemon.presentation.reservation.navigateToReservation
import com.example.littlelemon.presentation.reservation.reservationScreen
import kotlinx.coroutines.launch

@Composable
fun Navigation(
    navController: NavHostController,
    isLoggedIn: Boolean,
    saveUser: (UserProfile) -> Unit
) {
    val startDestination = if (isLoggedIn)
        HomeRoute
    else
        OnboardingRoute
    var selectedScreenIndex by remember {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val onClickMenu = {
        selectedScreenIndex = when (navController.currentBackStackEntry?.destination?.route) {
            "home" -> 0
            "reservation" -> 1
            "profile" -> 2
            else -> 3
        }
        when (drawerState.currentValue) {
            DrawerValue.Closed -> scope.launch { drawerState.open() }
            DrawerValue.Open -> scope.launch { drawerState.open() }
        }
        Unit
    }
    LittleLemonNavDrawer(
        navigateToHome = navController::navigateToHome,
        navigateToProfile = navController::navigateToProfile,
        navigateToReservation = navController::navigateToReservation,
        drawerState = drawerState,
        selectedNavDrawerItemIndex = selectedScreenIndex,
        coroutineScope = scope
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            onboardingScreen(navController)
            homeScreen(navController, onClickMenu)
            menuDescriptionScreen(navController, onClickMenu)
            orderScreen(navController, onClickMenu)
            reservationScreen(navController, onClickMenu)
            profileScreen(navController)
        }
    }
}