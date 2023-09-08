package com.example.littlelemon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

@Composable
fun Onboarding(
    modifier: Modifier = Modifier,
    saveUser: (User) -> Unit
) {
    var userName by remember {
        mutableStateOf("")
    }
    var userSurName by remember {
        mutableStateOf("")
    }
    var userEmail by remember {
        mutableStateOf("")
    }

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
            { userName = it },
            textFieldLabel = "Jane",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        LabelTextInput(
            inputLabel = "Last Name",
            text = userSurName,
            { userSurName = it },
            textFieldLabel = "Doe",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        LabelTextInput(
            inputLabel = "Email",
            text = userEmail,
            { userEmail = it },
            textFieldLabel = "janedoe@example.com",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier.weight(1.0f))

        Button(
            onClick = {
                      saveUser(User(name = userName, surname = userSurName, email = userEmail))
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
                text = "Register",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

    }
}

data class User(
    val name: String,
    val surname: String,
    val email: String
)

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() = Onboarding(saveUser = {})


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
fun LabelTextInput(
    inputLabel: String,
    text: String,
    onTextChange: (String) -> Unit,
    textFieldLabel: String,
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
            shape = RoundedCornerShape(8.dp)
        )
    }

}