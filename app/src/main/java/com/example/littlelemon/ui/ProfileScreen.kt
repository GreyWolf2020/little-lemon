package com.example.littlelemon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.littlelemon.MyApp
import com.example.littlelemon.data.userprofile.UserProfile
import com.example.littlelemon.presentation.ProfileViewModel
import com.example.littlelemon.presentation.viewModelFactory
import com.example.littlelemon.ui.common.OnboardingTopAppBar

fun NavGraphBuilder.profileScreen() {
    composable("profile") {
        val viewModel = viewModel<ProfileViewModel>(
            factory = viewModelFactory {
                ProfileViewModel(MyApp.appModule.userProfileRepository)
            }
        )
    }
}

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    modifier: Modifier = Modifier,

    ) {
    val profile by profileViewModel.userProfile.collectAsState()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingTopAppBar(
            Modifier.fillMaxWidth()
        )

        UserProfileInfo(profile)


        Button(
            onClick = {
                profileViewModel.removeUserProfile()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4CE14)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Log Out",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

    }
}

@Composable
fun ColumnScope.UserProfileInfo(
    profile: UserProfile,
    modifier: Modifier = Modifier
) = Column(
    verticalArrangement = Arrangement.Top
) {
    Text(
        text = "Personal information",
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF495E57),
        textAlign = TextAlign.Left,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 40.dp, bottom = 35.dp),
    )

    LabelTextInput(
        inputLabel = "First Name",
        text = profile.name,
        onTextChange = {},
        textFieldLabel = profile.name,
        isReadOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
    LabelTextInput(
        inputLabel = "Last Name",
        text = profile.surname,
        onTextChange = {},
        textFieldLabel =profile.surname,
        isReadOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
    LabelTextInput(
        inputLabel = "Email",
        text = profile.email,
        onTextChange = {},
        textFieldLabel = profile.email,
        isReadOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
}
