package com.example.littlelemon.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.local.menu.MenuItemLocal
import com.example.littlelemon.data.local.menu.MenuRepository
import com.example.littlelemon.data.local.userorder.UserOrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private val TAG = "HOMEVIEWMODEL"
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    val menuRepository: MenuRepository,
    val userOrderRepository: UserOrderRepository
) : ViewModel() {
    private val _allCategories = MutableStateFlow<AllCategories>(AllCategories())
    internal val allCategories = _allCategories.asStateFlow()

    private val _dishes = MutableStateFlow<List<Dish>>(listOf<Dish>())
    val dishes = _dishes.asStateFlow()

    private val _dishName = MutableStateFlow<String>("")
    val dishName = _dishName.asStateFlow()

    private val allDishes = MutableStateFlow<List<Dish>>(listOf())

    private val _isUserOrderEmpty = MutableStateFlow<Boolean>(false)
    internal val isUserOrderEmpty = _isUserOrderEmpty.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                menuRepository.getMenu().collect { menuItems ->
                    allDishes.update {
                        menuItems.map(MenuItemLocal::toDish)
                    }
                    _dishes.update {
                        menuItems.map(MenuItemLocal::toDish)
                    }
                }
            }
            launch {
                userOrderRepository.isOrderEmpty().collect {
                    _isUserOrderEmpty.update { it }
                }
            }

        }
    }

    fun onCategoryClicked(category: DishCategory): Unit {
        when (category) {
            is Desserts -> {
                _allCategories.update { categories ->
                    categories.copy(
                        desserts = Desserts(!category.isSelected)
                    )
                }
                applyFilters()
            }
            is Mains -> {
                _allCategories.update { categories ->
                    categories.copy(
                        mains = Mains(!category.isSelected)
                    )
                }
                applyFilters()
            }
            is Sides -> {
                _allCategories.update { categories ->
                    categories.copy(
                        sides = Sides(!category.isSelected)
                    )
                }
                applyFilters()
            }
            is Starters -> {
                _allCategories.update { categories ->
                    categories.copy(
                        starters = Starters(!category.isSelected)
                    )
                }
                applyFilters()
            }
        }
    }

    private fun applyFilters() {
        _dishes.update {
            val categories = _allCategories
                .value
            val listOfFilterCategories = if (categories.nonSelected)
                categories.list
            else
                categories
                    .list
                    .filter(DishCategory::isSelected)
            val listOfFilter = listOfFilterCategories
                .map {
                    it.name.lowercase()
                }
            Log.d(TAG, listOfFilter.toString())
            allDishes
                .value
                .filter {
                    it.category in listOfFilter
                }
        }
    }

    fun onDishNameChanged(newDishName: String) {
        _dishName.update {
            newDishName
        }
    }

    fun onSearch() {
        applyFilters()
        _dishes.update {
            it
                .filter {
                    it.name.contains(_dishName.value, ignoreCase = true)
                }
        }
    }
}

data class Dish(
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val category: String
) {
    var dishCnt = 0
    fun incrementDishCnt() = dishCnt++
    fun decrementDishCount(): Unit {
        if (dishCnt <= 0)
            return
        dishCnt--
    }
}

fun MenuItemLocal.toDish() : Dish = Dish(
    name = title,
    description = description,
    price = price,
    imageUrl = image,
    category = category
)

sealed class DishCategory(
    val isSelected: Boolean,
    val name: String
)
data class Sides(private val _isSelected: Boolean = false, private val _name: String = "Sides") : DishCategory(_isSelected, _name)
data class Mains(private val _isSelected: Boolean = false, private val _name: String = "Mains") : DishCategory(_isSelected, _name)
data class Desserts(private val _isSelected: Boolean = false, private val _name: String = "Desserts") : DishCategory(_isSelected, _name)
data class Starters(private val _isSelected: Boolean = false, private val _name: String = "Starters") : DishCategory(_isSelected, _name)
data class AllCategories(
    private val sides: DishCategory = Sides(false),
    private val mains: DishCategory = Mains(false),
    private val desserts: DishCategory = Desserts(false),
    private val starters: DishCategory = Starters(false)
) {
    val list: List<DishCategory>
        get() = listOf(
            sides,
            mains,
            desserts,
            starters
        )

    val anySelected: Boolean = sides.isSelected || mains.isSelected || desserts.isSelected || starters.isSelected

    val allSelected: Boolean = sides.isSelected && mains.isSelected && desserts.isSelected && starters.isSelected

    val nonSelected: Boolean = !anySelected
}