package com.example.littlelemon.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.littlelemon.ui.theme.Dimensions

@Composable
fun BasketFab(
    modifier: Modifier = Modifier,
    isBasketEmpty: Boolean = false,
    goToOrder: () -> Unit = {}
) {
    if (!isBasketEmpty)
        FloatingActionButton(
            modifier = modifier,
            elevation = FloatingActionButtonDefaults
                .bottomAppBarFabElevation(
                    defaultElevation = Dimensions.small

                ),
            contentColor = MaterialTheme.colorScheme.background,
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = goToOrder
        ) {
            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Shopping Cart")
        }
}
