package com.example.littlelemon.presentation.menudescription

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun MenuItemDetails(
    name: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = AppTheme.dimens.medium, bottom = AppTheme.dimens.xxxSmall)
        )
    }
}

@Preview
@Composable
fun MenuItemDetailsPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    MenuItemDetails(
        name = "Bruschetta",
        description = "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil. Topped with chopped tomatoes, oregano and fresh bazil.",
    )
}