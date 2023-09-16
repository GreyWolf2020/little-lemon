package com.example.littlelemon.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.example.littlelemon.UserProfileMessage
import com.example.littlelemon.data.UserProfileSerializer.userProfileDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

private const val TAG = "UserProfileRepoImpl"
class UserProfileRepoImpl(
    val context: Context
) : UserProfileRepository {
    override suspend fun saveUserProfile(userProfile: UserProfile) {
        context
            .userProfileDataStore
            .updateData { pref ->
                pref.toBuilder()
                    .setFirstName(userProfile.name)
                    .setSurname(userProfile.surname)
                    .setEmail(userProfile.email)
                    .build()
            }
    }

    override suspend fun getUserProfile(): Flow<UserProfile> {
        return context.userProfileDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.d(TAG, "Error reading UserProfileMessage from datastore", exception)
                    emit(UserProfileMessage.getDefaultInstance())
                }
                else throw exception
            }
            .map {
                UserProfile(
                    it.firstName,
                    it.surname,
                    it.email
                )
            }
    }

    override fun useIsLogged(): Flow<Boolean> = context
        .userProfileDataStore
        .data
        .map {
            it.firstName.isNotBlank()
        }

    override suspend fun deleteUserProfile() {
        context
            .userProfileDataStore
            .updateData { pref ->
                pref.toBuilder().clear().build()
            }
    }
}