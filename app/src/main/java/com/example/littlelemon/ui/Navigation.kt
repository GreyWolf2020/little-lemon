package com.example.littlelemon.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.littlelemon.MyApp
import com.example.littlelemon.data.userprofile.UserProfile
import com.example.littlelemon.presentation.ProfileViewModel
import com.example.littlelemon.presentation.viewModelFactory

@Composable
fun Navigation(
    navController: NavHostController,
    isLoggedIn: Boolean,
    saveUser: (UserProfile) -> Unit
) {
    val startDestination = if (isLoggedIn)
        Home.route
    else
        Onboarding.route

    NavHost(navController = navController, startDestination = startDestination) {
        onboardingScreen()
        homeScreen()
        menuDescriptionScreen()
        orderScreen()
        profileScreen()
        reservationScreen()
    }
}