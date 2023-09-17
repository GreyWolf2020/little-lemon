package com.example.littlelemon.di

import android.content.Context
import com.example.littlelemon.data.userprofile.UserProfileRepoImpl
import com.example.littlelemon.data.userprofile.UserProfileRepository


interface AppModule {
    val userProfileRepository: UserProfileRepository
}

class AppModuleImpl(
    private val context: Context
) : AppModule {
    override val userProfileRepository: UserProfileRepository
        get() = UserProfileRepoImpl(context)
}