package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.protobuf.type

private const val dishIdArg = "dishId"
fun NavGraphBuilder.menuDescriptionScreen(

) {
    composable(
        "menuDescriptionScreen/{$dishIdArg}",
        arguments = listOf(
            navArgument(dishIdArg) {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { backStackEntry ->
        MenuDescriptionScreen(
            DishId = backStackEntry.arguments?.getString(dishIdArg)
        )
    }

}

@Composable
fun MenuDescriptionScreen(DishId: String?) {

}