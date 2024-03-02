package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.highlightVariant
import com.example.littlelemon.ui.theme.tonalButtonSelected

@Composable
internal fun ReservationSection(
    allSections: List<ReservationSection>,
    selectedSection: ReservationSection,
    onSectionSelected: (ReservationSection) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimensions.medium)
    ) {
        items(allSections) { section ->
            val containerColor = if (section != selectedSection) MaterialTheme.colorScheme.secondary
            else tonalButtonSelected
            val textColor = if (section != selectedSection) MaterialTheme.colorScheme.primary
            else highlightVariant
            FilledTonalButton(
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = containerColor
                ),
                onClick = { onSectionSelected(section) }
            ) {
                Text(
                    text = section.section,
                    color = textColor,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun ReservationSectionPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    ReservationSection(
        allSections = ReservationSection.allSections,
        selectedSection = ReservationInfo(),
        onSectionSelected = { }
     )
}
