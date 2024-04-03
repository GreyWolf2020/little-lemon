package com.example.littlelemon

import android.app.Application
import com.example.littlelemon.di.AppModule
import com.example.littlelemon.di.AppModuleImpl

class MyApp : Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}