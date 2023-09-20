package com.example.littlelemon.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <VM : ViewModel> viewModelFactory(initialzer: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initialzer() as T
        }
    }
}