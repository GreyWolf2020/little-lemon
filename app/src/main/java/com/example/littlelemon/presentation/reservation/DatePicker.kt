package com.example.littlelemon.presentation.reservation

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    showDatePicker: Boolean = false,
    now: Calendar = Calendar.getInstance(),
    dismissDatePicker: (Long) -> Unit = {},
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = now.timeInMillis)
    var selectedDate by remember {
        mutableLongStateOf(now.timeInMillis)
    }
    if (showDatePicker) {
        DatePickerDialog(
            modifier = Modifier
                .padding(vertical = Dimensions.xxLarge, horizontal = Dimensions.small),
            onDismissRequest = { dismissDatePicker(selectedDate) },
            confirmButton = {
                TextButton(onClick = {
                    selectedDate = datePickerState.selectedDateMillis!!
                    dismissDatePicker(selectedDate)
                }) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dismissDatePicker(selectedDate)
                }){
                    Text(text = stringResource(R.string.cancel))
                }
            },
            shape = RoundedCornerShape(Dimensions.xSmall),
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            androidx.compose.material3.DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.primary,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.background,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    selectedDayContentColor = MaterialTheme.colorScheme.background,
                    weekdayContentColor = MaterialTheme.colorScheme.primary,
                    dayContentColor = MaterialTheme.colorScheme.primary,
                    navigationContentColor = MaterialTheme.colorScheme.primary,
                    currentYearContentColor = MaterialTheme.colorScheme.primary,
                    yearContentColor = MaterialTheme.colorScheme.primary,
                    )
            )
        }
    }
}

@Preview
@Composable
fun DatePickerDialogPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    DatePicker(
        false,
    ) {}
}


