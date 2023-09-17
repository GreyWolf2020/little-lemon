package com.example.littlelemon.data.userprofile

import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    suspend fun saveUserProfile(userProfile: UserProfile)
    suspend fun getUserProfile(): Flow<UserProfile>
    fun useIsLogged(): Flow<Boolean>

    suspend fun deleteUserProfile()
}