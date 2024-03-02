package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
internal fun LilyLemonTextInput(
    modifier: Modifier = Modifier,
    text: String,
    minLines: Int = 1,
    onTextChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable() (() -> Unit)? = null
) {

    OutlinedTextField(
        modifier = modifier
            .wrapContentHeight(),
        leadingIcon = leadingIcon,
        singleLine = if (minLines > 1) false else true,
        prefix = prefix,
        minLines = minLines,
        value = text,
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        textStyle = MaterialTheme.typography.labelMedium,
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
        text = "grgre",
        onTextChange = {},
        placeholder = "Name"
    )
}