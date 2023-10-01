package com.example.littlelemon.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.local.userprofile.UserProfile
import com.example.littlelemon.data.local.userprofile.UserProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun onUserNameChange(newUserName: String) {
        _userName.update {
            if (newUserName.isBlank()) ""
            else newUserName.first().uppercase() + newUserName.drop(1)
        }
    }

    fun onEmailChange(newEmail: String) {
        _email.update {
            newEmail
        }
    }

    fun onLastNameChange(newLastName: String) {
        _lastName.update {
            if (newLastName.isBlank()) ""
            else newLastName.first().uppercase() + newLastName.drop(1)
        }
    }

    fun onRegister() {
        viewModelScope.launch(Dispatchers.IO) {
            userProfileRepository
                .saveUserProfile(
                    UserProfile(name = _userName.value, surname = _lastName.value, email = _email.value)
                )
        }
    }
}