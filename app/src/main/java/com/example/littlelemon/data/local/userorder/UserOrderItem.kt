package com.example.littlelemon.data.local.userorder


data class UserOrderItem(
    val qty: Int,
    val name: String,
    val currency: Currency,
    val price: Double
)

enum class Currency() {
    ZW,
    USD
}