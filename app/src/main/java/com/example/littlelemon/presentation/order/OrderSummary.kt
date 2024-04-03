package com.example.littlelemon.presentation.order

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.highlightVariant

@Composable
fun OrderSummary(
    modifier: Modifier = Modifier,
    orders: List<Order> = listOf(sampleOrder, sampleOrderTwo, sampeOrderThree)
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.order_summary),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = Dimensions.large, vertical = Dimensions.large),

        )
        Text(
            text = stringResource(R.string.items),
            color = Color.Black,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = Dimensions.large, vertical = Dimensions.xSmall),

        )
        ItemsDesc(orders = orders)
    }
}

@Preview
@Composable
fun OrderSummaryPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    OrderSummary()
}

@Composable
fun ItemsDesc(
    modifier: Modifier = Modifier,
    orders: List<Order>
) {
    Column(
        modifier = modifier
            .padding(Dimensions.xxSmall)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Dimensions.xxSmall)
    ) {
        for (order in orders) {
            ItemDesc(order = order)
        }
    }
}

@Preview
@Composable
fun ItemsDescPreview() = LittleLemonTheme {
    ItemsDesc(orders = orders)
}

@Composable
fun ItemDesc(
    order: Order,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimensions.large),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth()
        ) {
            Text(
                text = order.qty.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.x),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = Dimensions.medium)
            )
            Text(
                text = order.descr,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier)
        Text(
            modifier = Modifier
                .weight(0.05f),
            text = "$",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            modifier = Modifier
                .weight(0.19f),
            text = String.format("%.2f" ,order.qty * order.unitPrice),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

data class Order(
    val qty: Int,
    val descr: String,
    val unitPrice: Double
)

val sampleOrder = Order(
    qty = 2,
    descr = "Bruschetta",
    unitPrice = 2.2
)
val sampleOrderTwo = Order(
    qty = 3,
    descr = "Creme Brulee",
    unitPrice = 5.5
)

val sampeOrderThree = Order(
    qty = 4,
    descr = "Croissant",
    unitPrice = 20.0
)

internal val orders = listOf(sampleOrder, sampleOrderTwo, sampeOrderThree)

@Preview
@Composable
fun ItemDescPreview() = LittleLemonTheme {
    ItemDesc(
        order = sampleOrder
    )
}