package com.example.littlelemon.presentation.reservation

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
internal fun ReviewInfoUI(
    modifier: Modifier = Modifier,
    customerReserveInfo: CustomerReservation
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
                text = customerReserveInfo.attendants
                    ?.let { attendants -> " $attendants" }
                    ?: " 0",
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
                text = customerReserveInfo.date?.let { date ->
                    SimpleDateFormat("dd-MM-yyyy")
                        .format(Date(date))
                } ?: "",
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
                text = customerReserveInfo.time
                    ?.let { time ->
                         " $time"
                    } ?: "",
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
                text = " ${customerReserveInfo.email}",
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
                text = " ${customerReserveInfo.phoneNum}",
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
        customerReserveInfo = CustomerReservation(
            salutation = Mr(),
            fullName = "James Variety Phiri",
            attendants = 2,
            date = null,
            time = null,
            email = "variety@hotmail.com",
            phoneNum = "0771 079 854",
            address = "183 Mbuyaehanda Street"
        )
    )
}
