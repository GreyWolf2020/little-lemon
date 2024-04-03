package com.example.littlelemon.presentation.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.presentation.common.MenuItem
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

private val dishImgHeight = 35.dp
@Composable
fun OrderMoreAd(
    modifier: Modifier = Modifier,
    dishes: List<Dish> = listOfDishes,
    onDishClicked: (String) -> Unit = {}
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = AppTheme.dimens.large,
            )
        ,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimensions.medium)
        ) {
            Text(
                text = "Add More Items to Your Order",
                style = MaterialTheme.typography.labelMedium
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimensions.small),
            ) {
                items(dishes) {dish ->
                    MenuItem(
                        dish = dish,
                        dishDescMaxLines = 2,
                        dishImageHeight = dishImgHeight,
                        onDishClicked = onDishClicked,
                        modifier = Modifier.fillParentMaxWidth(0.83f)
                    )
                }
            }
        }
    }
}

val listOfDishes = listOf(
    Dish(
        name = "Hero",
        description = "Dummy Image for using the hero Image. I am going to continues typing because these words are supposed to span at least three lines.",
        price = 100.01,
        imageUrl = "",
        category = "Mains",
        qty = 0
    ),
    Dish(
        name = "Hero",
        description = "Dummy Image for using the hero Image. I am going to continues typing because these words are supposed to span at least three lines.",
        price = 100.01,
        imageUrl = "",
        category = "Mains",
        qty = 0
    ),
    Dish(
        name = "Hero",
        description = "Dummy Image for using the hero Image. I am going to continues typing because these words are supposed to span at least three lines.",
        price = 100.01,
        imageUrl = "",
        category = "Mains",
        qty = 0
    )
)
@Preview
@Composable
fun OrderMoreAdPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    OrderMoreAd()
}