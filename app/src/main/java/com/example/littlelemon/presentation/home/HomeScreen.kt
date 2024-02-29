package com.example.littlelemon.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.littlelemon.MyApp
import com.example.littlelemon.presentation.common.LittleLemonNavDrawer
import com.example.littlelemon.presentation.profile.navigateToProfile
import com.example.littlelemon.presentation.util.viewModelFactory
import com.example.littlelemon.presentation.common.MyTopAppBar
import com.example.littlelemon.presentation.menudescription.navigateToMenuDescription
import com.example.littlelemon.presentation.reservation.navigateToReservation
import com.example.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.launch

const val HomeRoute = "home"
private const val HomeIndex = 0
fun NavGraphBuilder.homeScreen(
    navController: NavHostController,
    onClickMenu: () -> Unit
) {
    composable(HomeRoute) {
        val viewModel = viewModel<HomeViewModel>(
            factory = viewModelFactory {
                HomeViewModel(MyApp.appModule.menuRepository)
            }
        )
        HomeScreen(
            navigateToHome = navController::navigateToHome,
            navigateToMenuDesc = navController::navigateToMenuDescription,
            navigateToReservation = navController::navigateToReservation,
            navigateToProfile = navController::navigateToProfile,
            allCategories = viewModel.allCategories.collectAsState().value,
            dishName = viewModel.dishName.collectAsState().value,
            onDishNameChanged = viewModel::onDishNameChanged,
            onCategoryClicked = viewModel::onCategoryClicked,
            onClickSearch = viewModel::onSearch,
            dishes = viewModel.dishes.collectAsState().value,
            onClickMenu = onClickMenu
        )

    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    val navigationOptions = navOptions ?: NavOptions
        .Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(
            HomeRoute,
            false,
        )
        .build()
    if (this.currentBackStackEntry?.destination?.route != HomeRoute)
        this.navigate(HomeRoute, navigationOptions)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    navigateToHome: () -> Unit,
    navigateToMenuDesc: (String) -> Unit,
    navigateToReservation: () -> Unit,
    navigateToProfile: () -> Unit,
    allCategories: AllCategories,
    dishName: String,
    onDishNameChanged: (String) -> Unit,
    dishes: List<Dish>,
    onCategoryClicked: (DishCategory) -> Unit,
    onClickSearch: () -> Unit,
    onClickMenu: () -> Unit = {  },
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
          MyTopAppBar(
              navigateToHome = navigateToHome,
              onclickProfile = navigateToProfile,
              onclickMenu = onClickMenu
          )
        },
        modifier = modifier,
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                HeroSection(
                    dishName = dishName,
                    onClickReservation = navigateToReservation,
                    onClickSearch = onClickSearch,
                    onChangeDishName = onDishNameChanged
                )
                MenuBreakDown(
                    allDishCategories = allCategories,
                    onCategoryClicked = onCategoryClicked
                )
                MenuItems(
                    dishes = dishes,
                    onDishClicked = navigateToMenuDesc
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    HomeScreen(
        navigateToHome = {},
        navigateToMenuDesc = {},
        navigateToReservation = {},
        navigateToProfile = {  },
        allCategories = AllCategories(),
        dishName = "Dish",
        onDishNameChanged = {},
        dishes = List(4){
            Dish(
                name = "Name",
                description = "Something that is awesome or not, it just has to go to a lenght of more that six lines, Takakae, Hwaiting, Fighting, Bororo kusvika zvese zvaparara.",
                price = "11.00",
                imageUrl = "",
                category = ""
            )
        },
        onCategoryClicked = { },
        onClickSearch = { })
}
