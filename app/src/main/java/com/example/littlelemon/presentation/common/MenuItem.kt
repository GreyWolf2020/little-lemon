package com.example.littlelemon.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.littlelemon.presentation.home.Dish
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

private const val DISHDESCRMAXLINES = 3
private val dishImgHeight = 60.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    dish: Dish,
    dishDescMaxLines: Int = DISHDESCRMAXLINES,
    dishImageHeight: Dp = dishImgHeight,
    onDishClicked: (String) -> Unit
) {
    Column {
        Card(
            modifier = modifier,
            onClick = { onDishClicked(dish.name) },
            colors = CardDefaults
                .cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
        ) {
            Text(
                text = dish.name,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.65f)
                        .height(IntrinsicSize.Min),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = dish.description,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = dishDescMaxLines,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = AppTheme.dimens.xxSmall)
                    )
                    Text(
                        text = "$${dish.price}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }

                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(dish.imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.meal_placeholder),
                    alignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .weight(0.3f)
                        .height(dishImageHeight)
                        .padding(bottom = 2.dp)
                        .align(Alignment.Top),
                    contentDescription =  "image of ${dish.name}",
                    contentScale = ContentScale.Crop,

                    )
            }
        }
    }
}

@Preview
@Composable
fun MenuItemPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    MenuItem(
        dish = Dish(
            name = "Hero",
            description = "Dummy Image for using the hero Image. I am going to continues typing because these words are supposed to span at least three lines.",
            price = "100.01",
            imageUrl = "",
            category = "Mains"
        ),
        onDishClicked = {}
    )
}

