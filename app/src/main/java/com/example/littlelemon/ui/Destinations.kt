package com.example.littlelemon.ui

interface Destination {
    val route: String
}

object Home : Destination {
    override val route: String
        get() = "home"
}

object MenuDescription : Destination {
    override val route: String
        get() = "menu_description?dishId={$ARG_DISH_ID}"
    const val ARG_DISH_ID = "dishId"
}

object Onboarding : Destination {
    override val route: String
        get() = "onboarding"
}

object Order : Destination {
    override val route: String
        get() = "order"
}

object Profile : Destination {
    override val route: String
        get() = "profile"
}

object Reservation : Destination {
    override val route: String
        get() = "reservation"
}