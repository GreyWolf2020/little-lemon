package com.example.littlelemon.presentation.reservation

import android.graphics.drawable.VectorDrawable
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DatePicker(
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
            modifier = Modifier.padding(
                vertical = Dimensions.large, horizontal = Dimensions.xSmall
            ),
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
            ),
        ) {
            androidx.compose.material3.DatePicker(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(Dimensions.small)),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DatePickerInput(
    modifier: Modifier = Modifier,
    chosenDate: Long?,
    onClick: () -> Unit
) {
    val date = chosenDate?.let { dt ->
        val time = Date(dt)
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val current = formatter.format(time)
        current
    } ?: ""

    OutlinedTextField(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clickable { onClick() },
        maxLines = 1,
        readOnly = true,
        leadingIcon = {
              Icon(
                  imageVector = ImageVector.vectorResource(id = R.drawable.calendar)
                  , contentDescription = "Calender Icon"
              )
        },
        value = date,
        prefix = {
             Spacer(modifier = Modifier.width(Dimensions.medium))
        },
        placeholder = {
            Text(
                text = "Date",
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
}

@Preview
@Composable
private fun DatePickerInputPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    DatePickerInput(
        chosenDate = 0,
        onClick = {}
    )
}