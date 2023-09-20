package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.data.local.userprofile.UserProfileRepoImpl
import com.example.littlelemon.ui.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LittleLemonApp() {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val userProfileRepo = MyApp.appModule.userProfileRepository
    val isLoggedIn = userProfileRepo
        .useIsLogged()
        .collectAsState(initial = true ).value

    Navigation(navController = navController, isLoggedIn = isLoggedIn, saveUser = { user -> scope.launch(
        Dispatchers.IO) { userProfileRepo.saveUserProfile(user) }})
}