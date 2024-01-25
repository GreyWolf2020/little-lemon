package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.littlelemon.data.local.userprofile.UserProfile
import com.example.littlelemon.presentation.home.HomeRoute
import com.example.littlelemon.presentation.home.homeScreen
import com.example.littlelemon.presentation.menudescription.menuDescriptionScreen
import com.example.littlelemon.presentation.onboarding.OnboardingRoute
import com.example.littlelemon.presentation.onboarding.onboardingScreen
import com.example.littlelemon.presentation.order.orderScreen
import com.example.littlelemon.presentation.profile.profileScreen
import com.example.littlelemon.presentation.reservation.reservationScreen

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

    NavHost(navController = navController, startDestination = startDestination) {
        onboardingScreen(navController)
        homeScreen(navController)
        menuDescriptionScreen(navController)
        orderScreen(navController)
        reservationScreen(navController)
        profileScreen(navController)
    }
}