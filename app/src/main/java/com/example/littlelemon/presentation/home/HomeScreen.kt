package com.example.littlelemon.presentation.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.littlelemon.MyApp
import com.example.littlelemon.presentation.util.viewModelFactory

const val HomeRoute = "home"
fun NavGraphBuilder.homeScreen(

) {
    composable(HomeRoute) {
        val viewModel = viewModel<HomeViewModel>(
            factory = viewModelFactory {
                HomeViewModel(MyApp.appModule.menuRepository)
            }
        )
        HomeScreen(

        )
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HomeRoute, navOptions)
}

@Composable
internal fun HomeScreen(

) {

}