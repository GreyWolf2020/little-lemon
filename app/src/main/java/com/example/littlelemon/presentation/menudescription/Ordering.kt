package com.example.littlelemon.presentation.menudescription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.littlelemon.presentation.common.MyButton
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.karlaFamilyRegular

@Composable
fun Ordering(
    numOfDishes: String,
    dishPrice: String,
    topings: List<Toping>,
    onTopingSelected: (Toping) -> Unit,
    onIncNumOfDish: () -> Unit,
    onDecNumOfDish: () -> Unit,
    onAddToBasket: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Add",
            modifier = Modifier.padding(bottom = AppTheme.dimens.medium)
        )
        topings.forEach { toping ->
            Toping(toping = toping, onSelectToping = { onTopingSelected(toping) })
        }
        NumberOfItems(
            num = numOfDishes,
            onAdd = onIncNumOfDish,
            onSubract = onDecNumOfDish
        )
        AddToCartButton(
            onClickAddToCart = onAddToBasket,
            price = dishPrice
        )

    }
}

@Preview
@Composable
fun OrderingPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    Ordering(
        topings = listOf(
            Feta(),
            Pamersan(),
            Dressing(),
        ),
        numOfDishes = "1",
        dishPrice = "10.99",
        onTopingSelected = {},
        onIncNumOfDish = {},
        onDecNumOfDish = {   },
        onAddToBasket = {}
    )
}

@Composable
fun Toping(
    toping: Toping,
    onSelectToping: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(AppTheme.dimens.xLarge + AppTheme.dimens.xxSmall)
            .wrapContentHeight()
            .padding(top = AppTheme.dimens.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = toping.name,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.weight(1f, fill = true))
        Text(
            text = "$${toping.price}",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
        RadioButton(
            selected = toping.isSelected,
            onClick = onSelectToping,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}



@Preview
@Composable
fun TopingPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    Toping(
        toping = Feta(),
        onSelectToping = { /*TODO*/ }
    )
}

@Composable
fun NumberOfItems(
    num: String,
    onAdd: () -> Unit,
    onSubract: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = onAdd
        ) {
            Text(
                text = "+",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = karlaFamilyRegular,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = num,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = karlaFamilyRegular,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = AppTheme.dimens.small)
        )
        TextButton(
            onClick = onSubract
        ) {
            Text(
                text = "-",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = karlaFamilyRegular,
                color = MaterialTheme.colorScheme.primary

                )
        }
    }
}

@Preview
@Composable
fun NumberOfItemsPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    NumberOfItems(
        num = "1",
        onAdd = {},
        onSubract = {}
    )
}


@Composable
fun AddToCartButton(
    onClickAddToCart: () -> Unit,
    price: String
) {
    MyButton(
        onClick = onClickAddToCart,
        buttonText = "Add for $$price"
    )
}

@Preview
@Composable
fun AddToCartButtonPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    AddToCartButton(
        {},
        "1.00"
    )
}
