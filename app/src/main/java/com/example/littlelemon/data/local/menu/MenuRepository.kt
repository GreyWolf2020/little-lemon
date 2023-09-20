package com.example.littlelemon.data.local.menu

import com.example.littlelemon.data.remote.menu.MenuApi
import com.example.littlelemon.data.remote.menu.MenuNetworkData

interface MenuRepository {
    suspend fun getMenu(): MenuNetworkData
}

class MenuRepositoryImpl(
    val menuApi: MenuApi
) : MenuRepository {
    override suspend fun getMenu(): MenuNetworkData =
        menuApi.getMenu()

}