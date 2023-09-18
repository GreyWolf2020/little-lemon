package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeScreen(

) {
    composable("home") {
        HomeScreen()
    }
}


@Composable
internal fun HomeScreen() {

}