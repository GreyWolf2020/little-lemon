package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
internal fun ReviewInfoUI(
    modifier: Modifier = Modifier,
    customerReserveInfo: CustomerReserveInfo
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        )  {
            Text(
                text = stringResource(R.string.name),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.27f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " ${customerReserveInfo.fullName}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.73f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.xLarge))
        Row(
            verticalAlignment = Alignment.CenterVertically
        )  {
            Text(
                text = stringResource(R.string.table_for) + ":",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.27f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " " + customerReserveInfo.attendants,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.73f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        )  {
            Text(
                text = stringResource(R.string.date),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.27f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " ${customerReserveInfo.date}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.73f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        )  {
            Text(
                text = stringResource(R.string.time),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.27f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " ${customerReserveInfo.time}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.73f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.xLarge))
        Row(
            verticalAlignment = Alignment.CenterVertically
        )  {
            Text(
                text = stringResource(R.string.email),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.27f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " ${customerReserveInfo.emailAdd}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.73f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        )  {
            Text(
                text = stringResource(R.string.cell),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.27f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " ${customerReserveInfo.phonNum}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.73f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.xLarge))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.addr),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.27f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = " ${customerReserveInfo.address}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(0.73f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun ReviewInfoUIPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    ReviewInfoUI(
        modifier = Modifier.padding(horizontal = Dimensions.large),
        customerReserveInfo = CustomerReserveInfo(
            fullName = "James V. Phiri",
            address = "183 Mbuyanehanda Street",
            emailAdd = "variety@hotmail.com",
            phonNum = "0771 079 854",
            date = "23 Feb 2024",
            time = "10 00 Hrs",
            attendants = 3
        )
    )
}

internal data class CustomerReserveInfo(
    val fullName: String,
    val address: String,
    val emailAdd: String,
    val phonNum: String,
    val attendants: Short,
    val date: String,
    val time: String,
)