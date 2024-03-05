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
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun BasketFab(
    modifier: Modifier = Modifier,
    isBasketEmpty: Boolean = true,
    goToOrder: () -> Unit = {}
) {
    if (!isBasketEmpty)
        FloatingActionButton(
            modifier = modifier,
            elevation = FloatingActionButtonDefaults
                .bottomAppBarFabElevation(
                    defaultElevation = Dimensions.medium,
                    pressedElevation = Dimensions.large
                ),
            contentColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
            onClick = goToOrder
        ) {
            Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Shopping Cart")
        }
}

@Preview
@Composable
private fun BasketFabPreview() = LittleLemonTheme(darkTheme = false, dynamicColor = false) {
    BasketFab(isBasketEmpty = false)
}