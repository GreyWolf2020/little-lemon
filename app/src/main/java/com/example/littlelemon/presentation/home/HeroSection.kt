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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroSection(
    dish: String,
    onReservation: () -> Unit,
    onSearch: () -> Unit,
    onchangeDish: (String) -> Unit,
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
                modifier = Modifier
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
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Chicago",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .offset(0.dp, (-10).dp)
                        )
                        Text(
                            text = "We are a family owned Mediterranean restaurant focussed on traditional recipes served with a modern twist",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Button(
                        onClick = onReservation,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .fillMaxWidth(0.85f),
                        contentPadding = PaddingValues( horizontal = AppTheme.dimens.xxLarge)
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
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.xSmall))
            TextField(
                trailingIcon = { IconButton(onClick = onSearch) {
                    Icon(imageVector = Icons.Filled.Search , contentDescription = "Search Icon")
                }},
                value = dish,
                onValueChange = { dish -> onchangeDish(dish) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.primary
                ),
                textStyle = MaterialTheme.typography.labelMedium
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
        dish = "Fish",
        onSearch = {},
        onchangeDish = {},
        onReservation = {}
    )
}
