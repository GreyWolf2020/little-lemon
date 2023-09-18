package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.littlelemon.data.userprofile.UserProfile

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
        onboardingScreen()
        homeScreen()
        menuDescriptionScreen()
        orderScreen()
        profileScreen()
        reservationScreen()
    }
}