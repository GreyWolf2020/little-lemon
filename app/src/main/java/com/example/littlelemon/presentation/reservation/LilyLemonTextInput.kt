package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
internal fun LilyLemonTextInput(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        modifier = modifier,
        maxLines = 1,
        value = text,
        textStyle = MaterialTheme.typography.labelMedium,
        label = {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.primary
            )
        },
        onValueChange = { newText ->
            onTextChange(newText)
        },
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
private fun LilyLemonTextInputPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    LilyLemonTextInput(
        text = "Greg",
        onTextChange = {},
        label = "Name"
    )
}