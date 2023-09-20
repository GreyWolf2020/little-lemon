package com.example.littlelemon.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.local.userprofile.UserProfile
import com.example.littlelemon.data.local.userprofile.UserProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile>(UserProfile("", "",""))
    val userProfile = _userProfile.asStateFlow()

    init {
        getUserProfile()
    }
    private fun getUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val userProfile = userProfileRepository.getUserProfile().first()
            _userProfile.update {
                it.copy(
                    name = userProfile.name,
                    surname = userProfile.surname,
                    email = userProfile.email
                )
            }
        }
    }
    fun removeUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            userProfileRepository.deleteUserProfile()
        }

    }
}

