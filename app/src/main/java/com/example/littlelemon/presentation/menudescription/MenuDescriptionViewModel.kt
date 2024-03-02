package com.example.littlelemon.presentation.menudescription

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.local.menu.MenuRepository
import com.example.littlelemon.data.local.userorder.UserOrderRepository
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.presentation.home.toDish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val TAG = "MenuDescriptionViewModel"
class MenuDescriptionViewModel(
    val menuRepo: MenuRepository,
    val userOrderRepository: UserOrderRepository,
    private val dishName: String
) : ViewModel() {
    private val _dish = MutableStateFlow<Dish>(
        Dish(
            name = "",
            description = "",
            price = "",
            imageUrl = "",
            category = ""
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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _dish.update {
                menuRepo
                    .getMenuItem(dishName)
                    .toDish()
            }
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
    val price: String,
    val isSelected: Boolean
)

data class Dressing(val _name: String = "Dressing", val _price: String = "1.00", val _isSelected: Boolean = false) : Toping(_name, _price, _isSelected)

data class Feta(val _name: String = "Feta", val _price: String = "1.00", val _isSelected: Boolean = false) : Toping(_name, _price, _isSelected)

data class Pamersan(val _name: String = "Pamersan", val _price: String = "1.00", val _isSelected: Boolean = false) : Toping(_name, _price, _isSelected)