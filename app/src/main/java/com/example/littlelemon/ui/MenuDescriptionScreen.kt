package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val dishTitleArg = "dishTitle"
private const val MenuDescriptionRoute = "menuDescription"

internal class MenuDescriptionArg(val dishId: String) {
    constructor(savedStateHandle: SavedStateHandle): this(checkNotNull(savedStateHandle[dishTitleArg]) as String)
}
/* in viewModel
internal class ConversationViewModel(...,
                                     savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val conversationArgs = ConversationArgs(savedStateHandle)
}*/
fun NavGraphBuilder.menuDescriptionScreen(

) {
    composable(
        "$MenuDescriptionRoute/{$dishTitleArg}",
        arguments = listOf(
            navArgument(dishTitleArg) {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { backStackEntry ->
        MenuDescriptionScreen(
            DishId = backStackEntry.arguments?.getString(dishTitleArg)
        )
    }

}

fun NavController.navigateToMenuDescription(dishTitle: String) {
    this
        .navigate("MenuDescriptionRoute/$dishTitle")
}

@Composable
fun MenuDescriptionScreen(DishId: String?) {

}