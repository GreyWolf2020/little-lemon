package com.example.littlelemon.data.local.userorder

import android.content.Context
import android.util.Log
import com.example.littlelemon.UserOrderMessage
import com.example.littlelemon.UserOrderMessage.OrderItem
import com.example.littlelemon.data.local.userorder.UserOrderSerializer.userOrderDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val TAG = "UserOrderRepoImpl"
class UserOrderRepoImpl(
    val context: Context
) : UserOrderRepository {
    override suspend fun saveUserOrder(order: UserOrderItem) {
        Log.v(TAG, "Order being added is $order")
        context
            .userOrderDataStore
            .updateData { orders ->
                orders.toBuilder()
                    .addOrderItem(order.toOrderBuilder())
                    .build()
            }
    }

    override suspend fun getUserOrders(): Flow<List<UserOrderItem?>> = context
        .userOrderDataStore
        .data
        .catch { exception ->
            if (exception is IOException) {
                Log.d(TAG, "Error reading UserOrder message from datastore", exception)
                emit(UserOrderMessage.getDefaultInstance())
            }
        }.map { orders ->
            orders
                .orderItemList
                .map { maybeOrder ->
                    maybeOrder?.toOrder()
                }
        }

    override suspend fun isOrderEmpty(): Flow<Boolean> {
        val defaultMessage = UserOrderMessage.getDefaultInstance()
        return context
            .userOrderDataStore
            .data
            .catch { exception ->
                if (exception is IOException) {
                    emit(defaultMessage)
                }
            }.map {
                it == defaultMessage
            }

    }

    override suspend fun deleteAllUserOrders() {
        context
            .userOrderDataStore
            .updateData { orders ->
                orders.toBuilder().clear().build()
            }
    }
}

private fun Currency.toCurrency(): UserOrderMessage.Currency  = when (this) {
    Currency.ZW -> UserOrderMessage.Currency.ZW
    Currency.USD -> UserOrderMessage.Currency.USD
}

private fun UserOrderMessage.Currency.toCurrency(): Currency = when (this) {
    UserOrderMessage.Currency.USD -> Currency.USD
    else -> Currency.ZW
}

private fun OrderItem.toOrder(): UserOrderItem = UserOrderItem(
    qty = quantity,
    name = name,
    currency = currency.toCurrency(),
    price = price.toDouble()
)

private fun UserOrderItem.toOrderBuilder(): OrderItem.Builder = OrderItem
    .newBuilder()
    .setCurrency(currency.toCurrency())
    .setName(name)
    .setPrice(price.toFloat())
    .setQuantity(qty)
