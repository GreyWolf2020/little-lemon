package com.example.littlelemon.presentation.reservation

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.tonalButtonSelected

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TimePicker(
    modifier: Modifier = Modifier,
    selectedHour: Int = 0,
    selectedMinute: Int = 0,
    showDialog: Boolean = false,
    onDismiss: (DateNTime?) -> Unit = {}
) {
    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = { onDismiss(null) },
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background
                    )
                    .padding(vertical = Dimensions.large, horizontal = Dimensions.small),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // time picker
                TimeInput(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        selectorColor = MaterialTheme.colorScheme.surface,
                        timeSelectorSelectedContentColor = MaterialTheme.colorScheme.secondary,
                        timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.primary,
                        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary,
                        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondary,
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary,
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.primary,
                        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondary,
                        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.secondary,
                    )
                )

                // buttons
                Row(
                    modifier = Modifier
                        .padding(top = Dimensions.xSmall)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // dismiss button
                    TextButton(onClick = {
                        onDismiss(null)
                    }) {
                        Text(text = "Dismiss")
                    }

                    // confirm button
                    TextButton(
                        onClick = {
                            onDismiss(DateNTime(timePickerState.hour, timePickerState.minute))
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}

data class DateNTime(val date: Int, val time: Int)

@Preview
@Composable
private fun TimePickerPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    TimePicker(
        showDialog = true
    )
}