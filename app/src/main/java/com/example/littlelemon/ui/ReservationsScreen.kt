package com.example.littlelemon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.reservationScreen() {
    composable("reservation") {
        ReservationScreen()
    }
}

@Composable
fun ReservationScreen() {

}