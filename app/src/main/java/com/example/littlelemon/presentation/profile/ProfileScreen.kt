package com.example.littlelemon.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.littlelemon.MyApp
import com.example.littlelemon.data.local.userprofile.UserProfile
import com.example.littlelemon.presentation.util.viewModelFactory
import com.example.littlelemon.presentation.onboarding.LabelTextInput
import com.example.littlelemon.presentation.common.OnboardingTopAppBar

private const val ProfileRoute = "profile"
fun NavGraphBuilder.profileScreen(
    navController: NavHostController
) {
    composable(ProfileRoute) {
        val viewModel = viewModel<ProfileViewModel>(
            factory = viewModelFactory {
                ProfileViewModel(MyApp.appModule.userProfileRepository)
            }
        )
        val profileState by viewModel.userProfile.collectAsState()
        ProfileScreen(
            profileState = profileState,
            removeProfile = viewModel::removeUserProfile
        )
    }
}

fun NavController.navigateToProfile() {
        this.navigate(ProfileRoute)
}

@Composable
fun ProfileScreen(
    profileState: UserProfile,
    removeProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val profile = profileState
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
            onClick = removeProfile,
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
fun UserProfileInfo(
    profile: UserProfile,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
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
            .padding(horizontal = 20.dp),
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
