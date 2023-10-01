package com.example.littlelemon.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroSection(
    dishName: String,
    onChangeDishName: (String) -> Unit,
    onClickReservation: () -> Unit,
    onClickSearch: () -> Unit,
    modifier: Modifier = Modifier

) {

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(
                horizontal = AppTheme.dimens.large
            )
        ) {
            Text(
                text = "Little Lemon",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.height(64.dp)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.Top,

            ) {
                Column(
                    modifier = Modifier
                        .weight(0.6f)
                        .fillMaxHeight()
                        .offset(x = 0.dp, y = -10.dp),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.xSmall),
                ) {
                    Text(
                        text = "Chicago",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.height(40.dp)
                    )
                    Text(
                        text = "We are a family owned Mediterranean restaurant focussed on traditional recipes served with a modern twist",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Button(
                        onClick = onClickReservation,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        modifier = Modifier
                            .height(34.dp)
                            .fillMaxWidth(0.9f),
                        contentPadding = PaddingValues( horizontal = 0.dp)
                    ) {
                        Text(
                            text = "Reservation",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                Image(
                    painter = painterResource(R.drawable.hero_image),
                    contentDescription = "Hero section image",
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(AppTheme.dimens.xSmall)
                        )
                        .weight(0.35f)
                        .height(172.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.xxxSmall))
            TextField(
                trailingIcon = { IconButton(onClick = onClickSearch) {
                    Icon(imageVector = Icons.Filled.Search , contentDescription = "Search Icon")
                }},
                value = dishName,
                onValueChange = { dish -> onChangeDishName(dish) },
                modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.primary
                ),

            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.xSmall))
        }
        
    }
}

@Preview
@Composable
fun HeroSectionPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    HeroSection(
        dishName = "Fish",
        onClickSearch = {},
        onChangeDishName = {},
        onClickReservation = {}
    )
}
