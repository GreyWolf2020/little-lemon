package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.littlelemon.ui.Home
import com.example.littlelemon.ui.HomeScreen
import com.example.littlelemon.ui.MenuDescription
import com.example.littlelemon.ui.MenuDescriptionScreen
import com.example.littlelemon.ui.Onboarding
import com.example.littlelemon.ui.OnboardingScreen
import com.example.littlelemon.ui.Order
import com.example.littlelemon.ui.OrderScreen
import com.example.littlelemon.ui.Profile
import com.example.littlelemon.ui.ProfileScreen
import com.example.littlelemon.ui.Reservation
import com.example.littlelemon.ui.ReservationScreen
import com.example.littlelemon.ui.User

@Composable
fun Navigation(
    navController: NavHostController,
    isLoggedIn: Boolean,
    saveUser: (User) -> Unit
) {
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