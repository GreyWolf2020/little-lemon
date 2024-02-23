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
    expDate: Long? = Date().time,
    onExpDateChange: (Long) -> Unit = {  }
) {
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
    ) {
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = cardNum,
            onTextChange = onCardNumChange,
            placeholder = "Card #"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = Dimensions.xxSmall)
        ) {
            LilyLemonTextInput(
                modifier = Modifier.weight(0.3f),
                text = cvv,
                onTextChange = onCvvChange,
                placeholder = "CVV"
            )
            Spacer(modifier =  Modifier.weight(0.05f),)
            LilyLemonTextInput(
                modifier = Modifier
                    .weight(0.65f)
                    .clickable { showDatePicker = !showDatePicker },
                text = expDate
                    ?.let { date ->
                        SimpleDateFormat("MM - yyyy")
                            .format(Date(date))
                    } ?: "",
                onTextChange = {},
                placeholder = "Exp Date",
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.calendar)
                        , contentDescription = "Calender Icon"
                    )
                },
                prefix = {
                    Spacer(modifier = Modifier.width(Dimensions.medium))
                }
            )
            DatePicker(
                showDatePicker = showDatePicker,
                dismissDatePicker = { newDate ->
                    onExpDateChange(newDate)
                    showDatePicker = !showDatePicker
                }
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.large))
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(),
            text = address,
            onTextChange = onAddressChange,
            placeholder = "Address",
            minLines = 4,
        )
        Spacer(modifier = Modifier.height(Dimensions.large))
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = phoneNum,
            onTextChange = onPhoneNumChange,
            placeholder = "Phone #"
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