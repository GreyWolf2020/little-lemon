package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HonorificsSelector(
    modifier: Modifier = Modifier,
) {
    val honorifics = listOf<Honorifics>(
        Mr(),
        Mrs(),
        Ms(),
    )
    var expanded by remember { mutableStateOf(false) }
    var textFieldValue: String by remember {mutableStateOf(honorifics.first().name)}
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = modifier
                .width(Dimensions.xxxLarge * 2.2f)
                .menuAnchor(),
            maxLines = 1,
            textStyle = MaterialTheme.typography.labelMedium,
            readOnly = true,
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                disabledTextColor = MaterialTheme.colorScheme.primary,
            )
        )
        ExposedDropdownMenu(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            honorifics.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = selectionOption.name,
                            color = Color.Black
                        )
                    },
                    onClick = {
                        textFieldValue = selectionOption.name
                        expanded = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Black,

                    ),
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

sealed class Honorifics(open val name: String)

data class Mr(override val name: String = "Mr") : Honorifics(name)
data class Mrs(override val name: String = "Mrs") : Honorifics(name)

data class Ms(override val name: String = "Ms") : Honorifics(name)

@Preview
@Composable
private fun HonoficSelector() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    HonorificsSelector()
}