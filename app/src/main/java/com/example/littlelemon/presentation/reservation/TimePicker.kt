package com.example.littlelemon.presentation.reservation

import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.tonalButtonSelected
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TimePicker(
    modifier: Modifier = Modifier,
    selectedHour: Int = 0,
    selectedMinute: Int = 0,
    showDialog: Boolean = false,
    onDismiss: (Time?) -> Unit = {}
) {
    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = {
                onDismiss(null)
           },
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
                    modifier = Modifier,
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
                            onDismiss(Time(timePickerState.hour.toShort(), timePickerState.minute.toShort()))
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TimePickerInput(
    modifier: Modifier = Modifier,
    selectedHour: Int?,
    selectedMinute: Int?,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val hour = selectedHour?.let { if (it < 10) "0$it" else it } ?: ""
    val minute = selectedMinute?.let { if (it < 10) " 0$it Hrs" else " $it Hrs" } ?: "" ?: ""

    OutlinedTextField(
        interactionSource = interactionSource,
        modifier = modifier
            .height(IntrinsicSize.Min),
        maxLines = 1,
        readOnly = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.time)
                , contentDescription = "Time Icon"
            )
        },
        value = "$hour$minute",
        prefix = {
            Spacer(modifier = Modifier.width(Dimensions.medium))
        },
        placeholder = {
            Text(
                text = "Time",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        textStyle = MaterialTheme.typography.labelMedium,
        onValueChange = {  },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        )
    )
    val isPressed = interactionSource.collectIsPressedAsState().value
    if (isPressed) onClick()
}


@Preview
@Composable
private fun TimePickerInputPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    TimePickerInput(
        selectedHour = 1,
        selectedMinute = 9,
        onClick = {}
    )
}