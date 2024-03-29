package com.example.littlelemon.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.littlelemon.MyApp
import com.example.littlelemon.data.local.userprofile.UserProfile
import com.example.littlelemon.presentation.util.viewModelFactory
import com.example.littlelemon.presentation.common.OnboardingTopAppBar
import com.example.littlelemon.ui.theme.LittleLemonTheme

val OnboardingRoute = "onboardingScreen"
fun NavGraphBuilder.onboardingScreen(
    navController: NavHostController
) {
    composable(OnboardingRoute) {
        val viewModel = viewModel<OnboardingViewModel>(
            factory = viewModelFactory {
                OnboardingViewModel(MyApp.appModule.userProfileRepository)
            }
        )
        OnboardingScreen(
            saveUser =  viewModel::onRegister,
            userEmail = viewModel.email.collectAsState().value,
            onUserEmailChanged = viewModel::onEmailChange,
            userName = viewModel.userName.collectAsState().value,
            onUserNameChange = viewModel::onUserNameChange,
            userSurname = viewModel.lastName.collectAsState().value,
            onUserSurnameChange = viewModel::onLastNameChange
        )
    }
}

fun NavController.navigetToOnboarding(navOptions: NavOptions? = null) {
    this.navigate(OnboardingRoute, navOptions)
}

@Composable
fun OnboardingScreen(
    userName: String,
    onUserNameChange: (String) -> Unit,
    userSurname: String,
    onUserSurnameChange: (String) -> Unit,
    userEmail: String,
    onUserEmailChanged: (String) -> Unit,
    saveUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingTopAppBar(
            Modifier.fillMaxWidth()
        )
        Text(
            text = "Lets get to know you",
            fontSize = 24.sp,
            color = Color(0xFFEDEFEE),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF495E57))
                .padding(vertical = 40.dp),
        )
        Text(
            text = "Personal information",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF495E57),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 40.dp, bottom = 40.dp),
        )
        LabelTextInput(
            inputLabel = "First Name",
            text = userName,
            onUserNameChange,
            textFieldLabel = "Jane",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        LabelTextInput(
            inputLabel = "Last Name",
            text = userSurname,
            onUserSurnameChange,
            textFieldLabel = "Doe",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        LabelTextInput(
            inputLabel = "Email",
            text = userEmail,
            onUserEmailChanged,
            textFieldLabel = "janedoe@example.com",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier.weight(1.0f))

        Button(
            onClick = saveUser,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4CE14)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Register",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() = LittleLemonTheme(
    darkTheme = false,
    dynamicColor = false
) {
    OnboardingScreen(
        saveUser = {},
        onUserSurnameChange = {},
        onUserNameChange = {},
        onUserEmailChanged = {},
        userSurname = "Phiri",
        userName = "Gregory",
        userEmail = "gregphiri"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelTextInput(
    inputLabel: String,
    text: String,
    onTextChange: (String) -> Unit,
    textFieldLabel: String,
    isReadOnly: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = 40.dp)
    ) {
        Text(
            text = inputLabel,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = isReadOnly,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary
            )
        )
    }

}