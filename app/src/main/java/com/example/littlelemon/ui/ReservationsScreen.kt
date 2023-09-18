package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ReservationRoute = "reservation"
fun NavGraphBuilder.reservationScreen() {
    composable(ReservationRoute) {
        ReservationScreen()
    }
}

fun NavController.navigateToReservation() {
    this.navigate(ReservationRoute)
}

@Composable
fun ReservationScreen() {

}