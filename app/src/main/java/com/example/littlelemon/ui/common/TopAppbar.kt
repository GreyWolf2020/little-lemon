package com.example.littlelemon.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.AppTheme
import com.example.littlelemon.ui.theme.LittleLemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingTopAppBar(modifier: Modifier = Modifier) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.logo),
        contentDescription = "Logo image",
        modifier = modifier
            .padding(vertical = 20.dp)
            .height(40.dp),
        alignment = Alignment.Center
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingTopAppBarPreview() = OnboardingTopAppBar()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    navigateToHome: () -> Unit,
    onclickProfile: () -> Unit,
    onclickMenu: () -> Unit,
    modifier: Modifier = Modifier
    ) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                contentDescription = "Logo image",
                modifier = modifier
                    .fillMaxHeight()
                    .padding(
                        horizontal = AppTheme.dimens.xxLarge + AppTheme.dimens.large
                    )
                    .clickable { navigateToHome() },
                alignment = Alignment.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onclickMenu,   modifier = Modifier) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Hamburger menu icon",
                    tint = Color.Black,
                )
            }
        },
        actions = {
            IconButton(onClick = onclickProfile,   modifier = Modifier) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "User profile image",
                    tint = Color.Black,
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,

        ),
        modifier = modifier
    )
}

@Preview
@Composable
fun MyTopAppBarPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    MyTopAppBar(
        {},
        {},
        {}
    )
}