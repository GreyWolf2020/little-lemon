package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import java.util.Date


@Composable
internal fun ReservationInfoUI(
    modifier: Modifier = Modifier,
    honorific: Honorifics = Mrs(),
    onHonorificChange: (Honorifics) -> Unit = { } ,
    firstName: String = "",
    onFirstNameChange: (String) -> Unit = {  },
    middleNames: String = "",
    onMiddleNamesChange: (String) -> Unit = {   },
    surname: String = "",
    onSurnameChange: (String) -> Unit = {   },
    date: Long? = null,
    onDateChange: (Long?) -> Unit = {  },
    time: Time? = null,
    onTimeChange: (Time?) -> Unit = {  },
    attendants: Short = 0,
    incAttendants: () -> Unit = {  },
    decAttendants:  () -> Unit = {  },
) {

    Column(
        modifier = modifier
    ) {
        var showDate by remember {
            mutableStateOf(false)
        }
        var showTime by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HonorificsSelector(
                honorific = honorific,
                onHonorificChange = onHonorificChange
            )
            Spacer(modifier = Modifier.width(Dimensions.medium))
            LilyLemonTextInput(
                text = firstName,
                onTextChange = onFirstNameChange,
                placeholder = "First Name"
            )
        }
        LilyLemonTextInput(
            modifier = Modifier
                .padding(
                vertical = Dimensions.xxSmall
                )
                .fillMaxWidth(0.8f),
            text = middleNames,
            onTextChange = onMiddleNamesChange,
            placeholder = "Middle Names"
        )
        LilyLemonTextInput(
            modifier = Modifier
                .fillMaxWidth(0.6f),
            text = surname,
            onTextChange = onSurnameChange,
            placeholder = "Surname"
        )
        Spacer(modifier = Modifier.height(Dimensions.medium))
        DatePickerInput(
            modifier = Modifier
                .fillMaxWidth(0.55f),
            chosenDate = date,
            onClick = { showDate = !showDate }
        )
        DatePicker(
            showDatePicker = showDate,
            now = date ?.let { Date(it)} ?: Date(),
            dismissDatePicker = { newDate ->
                onDateChange(newDate)
                showDate = !showDate
            }
        )
        TimePickerInput(
            modifier = Modifier
                .padding(vertical = Dimensions.xxSmall)
                .fillMaxWidth(0.5f),
            selectedHour = time?.hr?.toInt(),
            selectedMinute = time?.min?.toInt(),
            onClick = { showTime = !showTime}
        )
        TimePicker(
            selectedHour = time?.hr?.toInt() ?: 0,
            selectedMinute = time?.min?.toInt() ?: 0,
            showDialog = showTime,
            onDismiss = { newTime ->
                onTimeChange(newTime)
                showTime = !showTime
            }
        )
        Spacer(modifier = Modifier.height(Dimensions.medium))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clickable { decAttendants() },
                text = "-",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 50.sp
            )
            Text(
                text = "Number of Diner\n$attendants",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier
                    .clickable { incAttendants() },
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
        modifier = Modifier.padding(horizontal = Dimensions.large),
        honorific = Mrs()
    )
}