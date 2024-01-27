package com.example.littlelemon.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.littlelemon.R
import com.example.littlelemon.presentation.common.MenuItem
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

private val dishImgHeight = 60.dp
@Composable
fun MenuItems(
    dishes: List<Dish>,
    onDishClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
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
        LazyColumn() {
            item {
                Divider(
                    thickness = Dp.Hairline,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = AppTheme.dimens.small)
                )
            }
            items(dishes) { dish ->
                MenuItem(
                    dish = dish,
                    dishImageHeight = dishImgHeight,
                    dishDescMaxLines = 3,
                    onDishClicked = onDishClicked
                )
                Divider(
                    thickness = Dp.Hairline,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = AppTheme.dimens.small)
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuItemsPreview() = LittleLemonTheme(
    dynamicColor = false,
    darkTheme = false
) {
    MenuItems(
        listOf(
            Dish(
                name = "Hero",
                description = "Dummy Image for using the hero Image. I am going to continues typing because these words are supposed to span at least three lines.",
                price = "100.01",
                imageUrl = "",
                category = "Mains"
            ),
            Dish(
                name = "Hero",
                description = "Dummy Image for using the hero Image. I am going to continues typing because these words are supposed to span at least three lines.",
                price = "100.01",
                imageUrl = "",
                category = "Mains"
            ),
            Dish(
                name = "Hero",
                description = "Dummy Image for using the hero Image. I am going to continues typing because these words are supposed to span at least three lines.",
                price = "100.01",
                imageUrl = "",
                category = "Mains"
            )
        ), {}
    )
}
