package com.example.littlelemon.presentation.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun OrderTotality(
    modifier: Modifier = Modifier,
    listOfOrders: List<Order> = orders,
    deliveryCost: Double = 12.00,
    serviceCost: Double = 12.00
) {
    val subTotal = listOfOrders
        .sumOf { order ->
            order.qty * order.unitPrice
        }
    val total = subTotal + deliveryCost + serviceCost
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.large)
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        bottom = Dimensions.medium
                    )
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.8f),
                    text = stringResource(R.string.subtotal),
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier=Modifier)
                Text(
                    modifier = Modifier
                        .weight(0.05f),
                    text = "$",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .weight(0.19f),
                    text = String.format("%.2f", subTotal),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                    vertical = Dimensions.xxSmall
                )
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.8f),
                    text = stringResource(R.string.delivery),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier=Modifier)
                Text(
                    modifier = Modifier
                        .weight(0.05f),
                    text = "$",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .weight(0.19f),
                    text = String.format("%.2f", deliveryCost),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        vertical = Dimensions.xxSmall
                    )
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.8f),
                    text = stringResource(R.string.Service),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier=Modifier)
                Text(
                    modifier = Modifier
                        .weight(0.05f),
                    text = "$",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .weight(0.19f),
                    text = String.format("%.2f", serviceCost),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        top = Dimensions.medium
                    )
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.8f),
                    text = stringResource(R.string.total),
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier=Modifier)
                Text(
                    modifier = Modifier
                        .weight(0.05f),
                    text = "$",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .weight(0.19f),
                    text = String.format("%.2f", total),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun OrderPricePreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    OrderTotality(

    )
}