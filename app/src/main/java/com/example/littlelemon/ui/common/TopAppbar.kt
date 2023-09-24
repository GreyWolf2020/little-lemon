package com.example.littlelemon.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
                    .padding(vertical = 10.dp)
                    .clickable { navigateToHome() },
                alignment = Alignment.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onclickMenu) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Hamburger menu icon")
            }
        },
        actions = {
            IconButton(onClick = onclickProfile) {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "User profile image",
                    alignment = Alignment.Center
                )
            }
        }
    )
}

@Preview
@Composable
fun MyTopAppBarPreview() = MaterialTheme {
    MyTopAppBar(
        {},
        {},
        {}
    )
}