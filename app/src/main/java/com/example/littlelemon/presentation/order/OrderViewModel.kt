package com.example.littlelemon.presentation.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.local.menu.MenuItemLocal
import com.example.littlelemon.data.local.menu.MenuRepository
import com.example.littlelemon.data.local.userorder.UserOrderItem
import com.example.littlelemon.data.local.userorder.UserOrderRepository
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.presentation.home.toDish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "ORDERVIEWMODEL"

class OrderViewModel(
    val menuRepository: MenuRepository,
    val userOrderRepository: UserOrderRepository
) : ViewModel() {
    private val _dishes = MutableStateFlow<List<Dish>>(listOf<Dish>())
    val dishes = _dishes.asStateFlow()

    private val _serviceCost = MutableStateFlow(3.30)
    val serviceCost = _serviceCost.asStateFlow()

    private val _deliveryCost = MutableStateFlow(20.00)
    val deliveryCost = _deliveryCost.asStateFlow()

    private val _orders = MutableStateFlow(emptyList<Order>())
    val orders = _orders.asStateFlow()

    private val _addCutlery = MutableStateFlow<Boolean>(false)
    val addCutlery = _addCutlery.asStateFlow()

    private val _isOrdering = MutableStateFlow(false)
    val isOrdering = _isOrdering.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                menuRepository.getMenu().collect { menuItems ->
                    _dishes.update {
                        menuItems.map(MenuItemLocal::toDish)
                    }
                }
            }
            launch {
                val orders = userOrderRepository
                    .getUserOrders()
                    .map {
                        it
                            .map { it?.toOrder() }
                            .filterNotNull()
                    }
                    .first()
                _orders.update {
                    orders
                }

            }
        }
    }

    fun onCheckout() {
        _isOrdering.update { true }
    }

    fun onDoneCheckout() {
        _isOrdering.update { false }
        viewModelScope.launch(Dispatchers.IO) {
            userOrderRepository
                .deleteAllUserOrders()
        }
    }

    fun toggleAddCutlery() {
        _addCutlery.update {
            !it
        }
    }

}


private fun UserOrderItem.toOrder(): Order = Order(
    qty = qty,
    descr = name,
    unitPrice = price
)