package com.example.littlelemon.presentation.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun MyButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
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
            .height(34.dp)
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
fun MyButtonPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    MyButton(
        {},
        "Add for $12.99"
    )
}