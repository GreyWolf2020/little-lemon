package com.example.littlelemon.presentation.reservation

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.Dimensions
import com.example.littlelemon.ui.theme.LittleLemonTheme
import java.util.Locale

@Composable
private fun LittleLemonBranding(
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
                    .height(boxMaxHeight * 15 / 24)
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
                    .height(boxMaxHeight * 85 / 120)
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
        .height(260.dp)
    )
}

@Composable
private fun ReservationHeader(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),

    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = Dimensions.xxSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier= Modifier
                    .wrapContentSize(Alignment.CenterStart),
                onClick = onBackClicked,
                colors = IconButtonDefaults
                    .filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = Color.Black
                ),
                enabled = true
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button"
                )
            }
            Text(
                modifier = Modifier.absoluteOffset(x = -Dimensions.xSmall),
                text = stringResource(R.string.reservation).uppercase(Locale.ROOT),
                style = MaterialTheme.typography.labelMedium
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = Dimensions.large),
            text = stringResource(R.string.reservation_msg),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun ReservationHeaderPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    ReservationHeader(
        onBackClicked =  {}
    )
}

@Composable
internal fun BrandingReserveMsg(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    Box(modifier = modifier) {
        LittleLemonBranding(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .align(Alignment.TopStart)
        )
        ReservationHeader(
            modifier = Modifier
                .align(Alignment.BottomStart),
            onBackClicked =  onBackClicked
        )
    }
}

@Preview
@Composable
fun BrandingReserveMsgPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    BrandingReserveMsg(
        modifier = Modifier.height(293.dp),
        onBackClicked = {}
    )
}
