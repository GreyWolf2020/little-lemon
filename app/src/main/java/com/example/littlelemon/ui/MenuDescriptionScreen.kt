package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.protobuf.type

private const val dishIdArg = "dishId"
private const val MenuDescriptionRoute = "menuDescription"

internal class MenuDescriptionArg(val dishId: String) {
    constructor(savedStateHandle: SavedStateHandle): this(checkNotNull(savedStateHandle[dishIdArg]) as String)
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
        "$MenuDescriptionRoute/{$dishIdArg}",
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

fun NavController.navigateToMenuDescription(dishId: String) {
    this
        .navigate("MenuDescriptionRoute/$dishId")
}

@Composable
fun MenuDescriptionScreen(DishId: String?) {

}