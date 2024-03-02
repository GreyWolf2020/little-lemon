package com.example.littlelemon.data.local.userorder

import kotlinx.coroutines.flow.Flow

interface UserOrderRepository {
    suspend fun saveUserOrder(order: UserOrderItem)
    suspend fun getUserOrders(): Flow<List<UserOrderItem?>>

    suspend fun isOrderEmpty(): Flow<Boolean>
    suspend fun deleteAllUserOrders()

}