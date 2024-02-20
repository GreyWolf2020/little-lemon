package com.example.littlelemon.presentation.reservation

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun PaymentInfoUI(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = "",
            onTextChange = {},
            placeholder = "Card #"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = Dimensions.xxSmall)
        ) {
            LilyLemonTextInput(
                modifier = Modifier.weight(0.3f),
                text = "",
                onTextChange = {} ,
                placeholder = "CVV"
            )
            Spacer(modifier =  Modifier.weight(0.05f),)
            LilyLemonTextInput(
                modifier = Modifier.weight(0.65f),
                text = "",
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
        }
        Spacer(modifier = Modifier.height(Dimensions.large))
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            onTextChange = {},
            placeholder = "Address",
            minLines = 4,
        )
        Spacer(modifier = Modifier.height(Dimensions.large))
        LilyLemonTextInput(
            modifier = Modifier.fillMaxWidth(0.6f),
            text = "",
            onTextChange = { },
            placeholder = "Phone #"
        )
        LilyLemonTextInput(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = Dimensions.xxSmall),
            text = "",
            onTextChange = { },
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
    PaymentInfoUI(
        modifier = Modifier
    )
}