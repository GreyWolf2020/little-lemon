package com.example.littlelemon.presentation.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
internal fun LittleLemonBranding(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        BoxWithConstraints(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            val boxScope = this
            val boxMaxHeight = this.maxHeight
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxMaxHeight * 18 / 24)
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(horizontal = Dimensions.large),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Little Lemon",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.height(64.dp)

                )
                Text(
                    text = "Chicago",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.height(40.dp)
                )
            }
            Image(
                painter = painterResource(R.drawable.hero_image),
                contentDescription = "Hero section image",
                modifier = Modifier
                    .padding(end = Dimensions.large)
                    .align(Alignment.BottomEnd)
                    .height(boxMaxHeight * 8 / 12)
                    .fillMaxWidth(0.35f)
                    .clip(
                        RoundedCornerShape(AppTheme.dimens.xSmall)
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
private fun LittleLemonBrandingPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    LittleLemonBranding(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)

    )
}