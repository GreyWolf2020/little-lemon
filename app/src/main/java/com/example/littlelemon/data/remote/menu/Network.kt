package com.example.littlelemon.data.remote.menu

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val MenuUrl = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
@Serializable
data class MenuNetworkData(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
)


interface MenuApi {
    suspend fun getMenu(): MenuNetworkData

}

class MenuApiImpl(
    private val client: HttpClient
) : MenuApi {
    override suspend fun getMenu(): MenuNetworkData {
        val menuItems: MenuNetworkData = client
            .get {
                url(MenuUrl)
                contentType(ContentType.Application.Json)
            }
            .body()
        Log.d("MENUAPI", "${menuItems}")
        return menuItems
    }
}

