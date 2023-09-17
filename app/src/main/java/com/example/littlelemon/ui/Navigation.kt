package com.example.littlelemon.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.littlelemon.data.userprofile.UserProfile

@Composable
fun Navigation(
    navController: NavHostController,
    isLoggedIn: Boolean,
    saveUser: (UserProfile) -> Unit
) {
    Log.d("NavigationComposable", "isLoggedIn $isLoggedIn")
    val startDestination = if (isLoggedIn)
        Home.route
    else
        Onboarding.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Onboarding.route) {
            OnboardingScreen() { user ->
                saveUser(user)
            }
        }
        composable(Home.route) {
            HomeScreen()
        }
        composable(
            MenuDescription.route,
            arguments = listOf(navArgument(MenuDescription.ARG_DISH_ID) {
                type = NavType.StringType
                nullable = false
            })
        ) { backstackEntry ->
            MenuDescriptionScreen(
                backstackEntry.arguments?.getString(MenuDescription.ARG_DISH_ID)
            )
        }
        composable(Order.route) {
            OrderScreen()
        }
        composable(Profile.route) {
            ProfileScreen()
        }
        composable(Reservation.route) {
            ReservationScreen()
        }
    }
}