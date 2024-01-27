package com.example.littlelemon.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.highlightVariant
import com.example.littlelemon.ui.theme.tonalButtonSelected

@Composable
fun MenuBreakDown(
    allDishCategories: AllCategories,
    onCategoryClicked: (DishCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(vertical = AppTheme.dimens.small),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimens.large)
        ) {
            Text(
                text = "ORDER FOR DELIVERY",
                color = Color.Black,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = Dimensions.medium)
            )

            LazyRow (
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.medium)
            ) {
                items(allDishCategories.list) { category ->
                    val containerColor = if (!category.isSelected) MaterialTheme.colorScheme.secondary
                    else tonalButtonSelected
                    val textColor = if (!category.isSelected) MaterialTheme.colorScheme.primary
                    else highlightVariant
                    FilledTonalButton(
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = containerColor
                        ),
                        onClick = { onCategoryClicked(category) }
                    ) {
                        Text(
                            category.name,
                            color = textColor,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun MenuBreakDownPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    MenuBreakDown(
        allDishCategories = AllCategories(),
        onCategoryClicked = {}
    )
}
