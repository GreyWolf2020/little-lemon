package com.example.littlelemon.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.MyApp
import com.example.littlelemon.presentation.util.viewModelFactory
import com.example.littlelemon.ui.common.MyTopAppBar

const val HomeRoute = "home"
fun NavGraphBuilder.homeScreen(

) {
    composable(HomeRoute) {
        val viewModel = viewModel<HomeViewModel>(
            factory = viewModelFactory {
                HomeViewModel(MyApp.appModule.menuRepository)
            }
        )
        val navController = rememberNavController()
        HomeScreen(
            navigateToHome = { navController.navigateToHome() }
        )
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    if (this.currentBackStackEntry?.destination?.route != HomeRoute)
        this.navigate(HomeRoute, navOptions)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
          MyTopAppBar(
              navigateToHome = navigateToHome,
              onclickProfile = { /*TODO*/ },
              onclickMenu = { /*TODO*/ })
        },
        modifier = modifier,
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() = MaterialTheme {
    HomeScreen(
        {}
    )
}