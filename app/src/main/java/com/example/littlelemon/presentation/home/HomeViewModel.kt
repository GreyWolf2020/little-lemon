package com.example.littlelemon.presentation.home

import androidx.lifecycle.ViewModel
import com.example.littlelemon.data.local.menu.MenuRepository

private val TAG = "HOMEVIEWMODEL"
class HomeViewModel(
    val menuRepository: MenuRepository
) : ViewModel() {

}