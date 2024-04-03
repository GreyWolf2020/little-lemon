package com.example.littlelemon.presentation.reservation

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
internal fun PaymentInfoUI(
    modifier: Modifier = Modifier,
    cardNum: String = "",
    onCardNumChange: (String) -> Unit = {},
    cvv: String = "",
    onCvvChange: (String) -> Unit = {},
    address: String = "",
    onAddressChange: (String) -> Unit = { },
    phoneNum: String = "",
    onPhoneNumChange: (String) -> Unit = { },
    email: String = "",
    onEmailChange: (String) -> Unit = { },
    expDate: Long? = null,
    onExpDateChange: (Long) -> Unit = {  }
) {

    var showDate by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
    ) {
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = cardNum,
            onTextChange = onCardNumChange,
            placeholder = stringResource(R.string.card_num)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = Dimensions.xxSmall)
        ) {
            LilyLemonTextInput(
                modifier = Modifier.weight(0.25f),
                text = cvv,
                onTextChange = onCvvChange,
                placeholder = stringResource(R.string.cvv)
            )
            Spacer(modifier =  Modifier.weight(0.05f),)
            DatePickerInput(
                modifier = Modifier
                    .weight(0.60f),
                placeholder = stringResource(R.string.exp_date),
                dateFormat = "MM-yy",
                chosenDate = expDate,
                onClick = { showDate = !showDate }
            )
            DatePicker(
                showDatePicker = showDate,
                now = expDate ?.let { Date(it)  } ?: Date(),
                dismissDatePicker = { newDate ->
                    onExpDateChange(newDate)
                    showDate = !showDate
                }
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.large))
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(),
            text = address,
            onTextChange = onAddressChange,
            placeholder = stringResource(R.string.address),
            minLines = 4,
        )
        Spacer(modifier = Modifier.height(Dimensions.large))
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = phoneNum,
            onTextChange = onPhoneNumChange,
            placeholder = stringResource(R.string.phone_num)
        )
        LilyLemonTextInput(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = Dimensions.xxSmall),
            text = email,
            onTextChange = onEmailChange,
            placeholder = "Email"
        )

    }
}

@Preview
@Composable
fun PaymentInfoPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    PaymentInfoUI()
}
