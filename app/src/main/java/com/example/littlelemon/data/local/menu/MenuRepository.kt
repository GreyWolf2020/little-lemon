package com.example.littlelemon.data.local.menu

import com.example.littlelemon.data.remote.menu.MenuApi
import com.example.littlelemon.data.remote.menu.MenuItemNetwork
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getMenu(): Flow<List<MenuItemLocal>>
}

fun MenuItemNetwork.toMenuItemLocal(): MenuItemLocal = MenuItemLocal(
    id = id,
    title = title,
    description = description,
    price = price,
    image = image,
    category = category
)

class MenuRepositoryImpl(
    val menuApi: MenuApi,
    val menuItemDao: MenuItemDao
) : MenuRepository {
    override suspend fun getMenu(): Flow<List<MenuItemLocal>> = if (menuItemDao.isEmpty()) {
        val menuItemsRemote = menuApi.getMenu().menu
        menuItemDao.insertAll(
            *menuItemsRemote
                .map { it.toMenuItemLocal() }
                .toTypedArray()
        )
        menuItemDao.getAllMenuItems()
    } else
        menuItemDao.getAllMenuItems()
}