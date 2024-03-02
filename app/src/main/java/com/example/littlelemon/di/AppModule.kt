package com.example.littlelemon.di

import android.content.Context
import com.example.littlelemon.data.local.menu.MenuDatabase
import com.example.littlelemon.data.local.menu.MenuItemDao
import com.example.littlelemon.data.local.menu.MenuRepository
import com.example.littlelemon.data.local.menu.MenuRepositoryImpl
import com.example.littlelemon.data.local.userorder.UserOrderRepoImpl
import com.example.littlelemon.data.local.userorder.UserOrderRepository
import com.example.littlelemon.data.local.userprofile.UserProfileRepoImpl
import com.example.littlelemon.data.local.userprofile.UserProfileRepository
import com.example.littlelemon.data.remote.menu.MenuApi
import com.example.littlelemon.data.remote.menu.MenuApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json


interface AppModule {
    val userProfileRepository: UserProfileRepository
    val menuClient: HttpClient
    val menuRepository: MenuRepository
    val menuApi: MenuApi
    val menuItemDao: MenuItemDao
    val userOrderRepo: UserOrderRepository
}

class AppModuleImpl(
    private val context: Context
) : AppModule {
    override val userProfileRepository: UserProfileRepository
        get() = UserProfileRepoImpl(context)
    override val menuClient: HttpClient
        get() = HttpClient(Android) {
            install(ContentNegotiation) {
                json(contentType = ContentType("text", "plain"))
            }
        }
    override val menuRepository: MenuRepository
        get() = MenuRepositoryImpl(
            menuApi = menuApi,
            menuItemDao = menuItemDao
        )
    override val menuApi: MenuApi
        get() = MenuApiImpl(client = menuClient)
    override val menuItemDao: MenuItemDao
        get() = MenuDatabase
            .getDatabase(context)
            .menuItemDao()
    override val userOrderRepo: UserOrderRepository
        get() = UserOrderRepoImpl(context)

}