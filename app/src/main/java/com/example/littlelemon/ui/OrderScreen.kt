package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val OrderRoute = "order"
fun NavGraphBuilder.orderScreen(

) {
    composable(OrderRoute) {
        OrderScreen()
    }
}

fun NavController.navigateToOrder() {
    this.navigate(OrderRoute)
}

@Composable
fun OrderScreen() {

}
