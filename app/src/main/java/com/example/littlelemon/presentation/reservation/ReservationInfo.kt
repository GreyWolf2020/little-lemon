package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme


@Composable
internal fun ReservationInfoUI(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HonorificsSelector()
            Spacer(modifier = Modifier.width(Dimensions.medium))
            LilyLemonTextInput(
                text = "Grega",
                onTextChange = {},
                placeholder = "First Name"
            )
        }
        LilyLemonTextInput(
            modifier = Modifier
                .padding(
                vertical = Dimensions.xxSmall
                )
                .fillMaxWidth(0.8f),
            text = "",
            onTextChange = {},
            placeholder = "Middle Names"
        )
        LilyLemonTextInput(
            modifier = Modifier
                .fillMaxWidth(0.6f),
            text = "",
            onTextChange = {},
            placeholder = "Surname"
        )
        Spacer(modifier = Modifier.height(Dimensions.medium))
        DatePickerInput(
            modifier = Modifier
                .fillMaxWidth(0.55f),
            chosenDate = null,
            onClick = {}
        )
        DatePicker()
        TimePickerInput(
            modifier = Modifier
                .padding(vertical = Dimensions.xxSmall)
                .fillMaxWidth(0.5f),
            selectedHour = null,
            selectedMinute = null,
            onClick = {}
        )
        TimePicker()
        Spacer(modifier = Modifier.height(Dimensions.medium))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "-",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 50.sp
            )
            Text(
                text = "Number of Diner\n1",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "+",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 50.sp
            )
        }

    }
}


@Preview
@Composable
private fun ReservationInfoPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    ReservationInfoUI(
        modifier = Modifier
    )
}