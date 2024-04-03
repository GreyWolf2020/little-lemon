package com.example.littlelemon.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Dimensions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal data class NavDrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val navigate: () -> Unit
)

@Composable
fun LittleLemonNavDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    selectedNavDrawerItemIndex: Int?,
    navigateToHome: () -> Unit,
    navigateToReservation: () -> Unit,
    navigateToProfile: () -> Unit,
    coroutineScope: CoroutineScope,
    navHostContent: @Composable () -> Unit,
) {
    val drawerItems = listOf<NavDrawerItem>(
        NavDrawerItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            navigate = navigateToHome
        ),
        NavDrawerItem(
            title = "Reservation",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.book_online_filled),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.book_online_outlined),
            navigate =navigateToReservation
        ),
        NavDrawerItem(
            title = "Profile",
            selectedIcon = Icons.Filled.AccountBox,
            unselectedIcon = Icons.Outlined.AccountBox,
            navigate = navigateToProfile
        )
    )
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(Dimensions.large))
                    drawerItems.forEachIndexed { index, navDrawerItem ->
                        NavigationDrawerItem(
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedIconColor = Color.White,
                                unselectedTextColor = Color.White,
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary
                            ),
                            label = {
                                Text(text = navDrawerItem.title)
                            },
                            selected = index == selectedNavDrawerItemIndex,
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.close()
                                    navDrawerItem.navigate()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector =
                                        if (index == selectedNavDrawerItemIndex)
                                            navDrawerItem.selectedIcon
                                        else navDrawerItem.unselectedIcon,
                                    contentDescription = navDrawerItem.title
                                )
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        ) {
            navHostContent()
        }
    }
}


