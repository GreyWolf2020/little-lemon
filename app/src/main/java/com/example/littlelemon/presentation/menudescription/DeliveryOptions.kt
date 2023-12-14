package com.example.littlelemon.presentation.menudescription

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun DeliveryOptions(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.delivery_van),
                contentDescription = "Delivery van image",
                modifier = Modifier
                    .height(18.dp)
            )
            Spacer(
                modifier = Modifier.width(15.dp)
            )
            Text(
                text = "Delivery time:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(
                modifier = Modifier.width(AppTheme.dimens.xxSmall)
            )
            Text(
                text = "30 mins",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        FilledTonalButton(
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            onClick = {

            }
        ) {
            Text(
                text = "Change",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Composable
fun DeliveryOptionsPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    DeliveryOptions()
}