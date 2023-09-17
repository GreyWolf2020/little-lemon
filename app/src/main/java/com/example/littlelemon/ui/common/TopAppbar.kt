package com.example.littlelemon.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingTopAppBar(modifier: Modifier = Modifier) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.logo),
        contentDescription = "Logo image",
        modifier = modifier
            .padding(vertical = 20.dp)
            .height(30.dp),
        alignment = Alignment.Center
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingTopAppBarPreview() = OnboardingTopAppBar()
