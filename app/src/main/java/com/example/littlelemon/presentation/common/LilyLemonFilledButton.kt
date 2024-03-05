package com.example.littlelemon.presentation.common

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun LilyLemonFilledButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enableButton: Boolean = true,
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val containerColor by animateColorAsState(targetValue = if (isPressed)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.tertiary)
    val textColor by animateColorAsState(targetValue = if (isPressed)
        Color.White
    else
        MaterialTheme.colorScheme.primary)
    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        modifier = modifier
            .height(37.dp)
            .fillMaxWidth(),
        enabled = enableButton,
        ) {
        Text(
            text = buttonText,
            color = textColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Composable
fun LilyLemonUnFilledButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val containerColor by animateColorAsState(targetValue = if (isPressed)
        MaterialTheme.colorScheme.tertiary
    else
        MaterialTheme.colorScheme.background
    )
    val textColor = MaterialTheme.colorScheme.primary
    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        ),
        border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.tertiary),
        modifier = modifier
            .height(37.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = buttonText,
            color = textColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview
@Composable
fun LilyLemonFilledButtonPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    LilyLemonFilledButton(
        {},
        "Add for $12.99",

    )
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Preview
@Composable
fun LilyLemonUnFilledButtonPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    LilyLemonUnFilledButton(
        {},
        "Add for $12.99",
    )
}