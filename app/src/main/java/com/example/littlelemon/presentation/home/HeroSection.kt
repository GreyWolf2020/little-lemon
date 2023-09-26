package com.example.littlelemon.presentation.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroSection(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier

) {
    var dish by rememberSaveable {
        mutableStateOf("")
    }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = AppTheme.dimens.xSmall
            ),
    ) {
        Column() {
            Text(text = "Little Lemon")
            Row {
                Column {
                    Text(text = "Chicago")
                    Text(text = "We are a family owned Mediterranean restaurant focussed on traditional recipes served with a morden twist")
                }
                Image(painter = painterResource(R.drawable.logo), contentDescription = "Hero section image")
            }
            Button(onClick = { onSearch(dish) }) {
                Text(text = "Reservation")
            }
            TextField(
                leadingIcon = { IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search , contentDescription = "Search Icon")
                }},
                value = dish,
                onValueChange = { dish = it }
            )
        }
        
    }
}


@Preview
@Composable
fun HeroSectionPreview() = MaterialTheme {
    HeroSection(onSearch = {})
}