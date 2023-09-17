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
            val profileViewModel = viewModel<ProfileViewModel>(
                factory = viewModelFactory {
                    ProfileViewModel(MyApp.appModule.userProfileRepository)
                }
            )
            ProfileScreen(profileViewModel)
        }
        composable(Reservation.route) {
            ReservationScreen()
        }
    }
}