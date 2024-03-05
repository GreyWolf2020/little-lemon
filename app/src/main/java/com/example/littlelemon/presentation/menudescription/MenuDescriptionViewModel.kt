package com.example.littlelemon.presentation.menudescription

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.local.menu.MenuRepository
import com.example.littlelemon.data.local.userorder.Currency
import com.example.littlelemon.data.local.userorder.UserOrderItem
import com.example.littlelemon.data.local.userorder.UserOrderRepository
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.presentation.home.toDish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

private const val TAG = "MenuDescriptionViewModel"
internal class MenuDescriptionViewModel(
    val menuRepo: MenuRepository,
    val userOrderRepository: UserOrderRepository,
    private val dishName: String
) : ViewModel() {

    private val _isAddingOrder = MutableStateFlow<Boolean>(false)
    internal val isAddingOrder = _isAddingOrder.asStateFlow()

    private val _dish = MutableStateFlow<Dish>(
        Dish(
            name = "",
            description = "",
            price = 0.00,
            imageUrl = "",
            category = "",
            qty = 0
        )
    )
    val dish = _dish
        .asStateFlow()
        .also { it.onCompletion { Log.d(TAG, it.toString()) } }

    private val feta = MutableStateFlow<Feta>(Feta())
    private val dressing = MutableStateFlow<Dressing>(Dressing())
    private val pamersan = MutableStateFlow<Pamersan>(Pamersan())
    val topings = combine(feta.asStateFlow(), dressing.asStateFlow(),pamersan.asStateFlow()) { f1, f2, f3 ->
        listOf(f1, f2, f3)
    }
    private val _isUserOrderEmpty = MutableStateFlow(true)
    internal val isUserOrderEmpty = _isUserOrderEmpty.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                _dish.update {
                    menuRepo
                        .getMenuItem(dishName)
                        .toDish()
                }
            }
            launch {
                val isOrderEmptyFlow = userOrderRepository.isOrderEmpty()
                isOrderEmptyFlow.collect { isOrderEmpty ->
                    _isUserOrderEmpty.update {
                        isOrderEmpty
                    }
                }
                _isUserOrderEmpty.update {
                    isOrderEmptyFlow.first()
                }
            }
        }
    }

    internal fun onOrder() {
        _isAddingOrder.update { true }
        viewModelScope.launch(Dispatchers.IO) {
            val toppingsCost = feta.value.toBePaid() + pamersan.value.toBePaid() + feta.value.toBePaid()
            userOrderRepository
                .saveUserOrder(
                    _dish.
                    value.
                    toUserOrderItem(toppingsCost)
                )
        }

    }

    internal fun onOrderDoneAck() {
        _isAddingOrder.update { false }
    }
    internal fun onDishCntInc() {
        _dish.update {
            it.copy(qty = it.qty + 1)
        }
    }

    internal fun onDishCntDec() {
        if (_dish.value.qty > 0)
            _dish.update {
                it.copy(qty = it.qty - 1)
            }
    }

     fun onTopingSelected(toping: Toping): Unit = when(toping) {
         is Dressing -> dressing.update { it.copy(_isSelected = !it._isSelected) }
         is Feta -> feta.update { it.copy(_isSelected = !it._isSelected) }
         is Pamersan -> pamersan.update { it.copy(_isSelected = !it._isSelected) }
     }
}

sealed class Toping(
    val name: String,
    val price: Double,
    val isSelected: Boolean
) {
    fun toBePaid(): Double = if (isSelected)
        price.toDouble()
    else 0.0
}

data class Dressing(val _name: String = "Dressing", val _price: Double = 1.00, val _isSelected: Boolean = false) : Toping(_name, _price, _isSelected)

data class Feta(val _name: String = "Feta", val _price: Double = 1.00, val _isSelected: Boolean = false) : Toping(_name, _price, _isSelected)

data class Pamersan(val _name: String = "Pamersan", val _price: Double = 1.00, val _isSelected: Boolean = false) : Toping(_name, _price, _isSelected)

private fun Dish.toUserOrderItem(additionalCosts: Double = 0.0): UserOrderItem = UserOrderItem(
    qty = qty,
    name = name,
    currency = Currency.USD,
    price = price.toDouble() + additionalCosts
)