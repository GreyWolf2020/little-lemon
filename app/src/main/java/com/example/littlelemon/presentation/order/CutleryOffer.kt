package com.example.littlelemon.presentation.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun CutleryOffer(
    modifier: Modifier = Modifier,
    isCutlerySelected: Boolean = false,
    onClickGetCutlery: (Boolean) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.large),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.64f)
            ) {
                Text(
                    text = stringResource(id = R.string.cutlery),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = stringResource(id = R.string.cutlery_green_message),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.weight(0.3f))
            RadioButton(
                selected = isCutlerySelected,
                onClick = { onClickGetCutlery(isCutlerySelected) },
                modifier = Modifier.weight(0.06f)
            )
        }
    }
}

@Preview
@Composable
fun CutleryOfferPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    CutleryOffer(
        isCutlerySelected = false,
        onClickGetCutlery = {}
    )
}