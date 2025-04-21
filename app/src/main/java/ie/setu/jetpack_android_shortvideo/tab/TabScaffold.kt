package ie.setu.jetpack_android_shortvideo.tab

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ie.setu.jetpack_android_shortvideo.nav.HomeNavHost
import ie.setu.jetpack_android_shortvideo.nav.DiscoverNavHost
import ie.setu.jetpack_android_shortvideo.nav.PublishNavHost
import ie.setu.jetpack_android_shortvideo.nav.UserCenterNavHost
import ie.setu.jetpack_android_shortvideo.viewmodel.SharedUiViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun TabScaffold(sharedUiViewModel: SharedUiViewModel) {
    val tabs = listOf("home", "discover", "publish", "user")
    var selectedTab by rememberSaveable { mutableStateOf("home") }

    val homeNavController = rememberNavController()
    val discoverNavController = rememberNavController()
    val publishNavController = rememberNavController()
    val userNavController = rememberNavController()

    // 👇 讀取是否顯示 TabBar 的狀態
    val isTabVisible = sharedUiViewModel.isTabVisible.collectAsState().value

    Scaffold(
        bottomBar = {
            if (isTabVisible) {
                NavigationBar {
                    tabs.forEach { tab ->
                        NavigationBarItem(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            icon = {
                                when (tab) {
                                    "home" -> Icon(Icons.Default.Home, contentDescription = null)
                                    "discover" -> Icon(Icons.Default.Search, contentDescription = null)
                                    "publish" -> Icon(Icons.Default.Add, contentDescription = null)
                                    "user" -> Icon(Icons.Default.Person, contentDescription = null)
                                    else -> Icon(Icons.Default.Info, contentDescription = null)
                                }
                            },
                            label = { Text(tab.replaceFirstChar { it.uppercaseChar() }) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "home" -> HomeNavHost(
                    navController = homeNavController,
                    sharedUiViewModel = sharedUiViewModel // 👈 傳進去
                )
                "discover" -> DiscoverNavHost(navController = discoverNavController)
                "publish" -> PublishNavHost(navController = publishNavController)
                "user" -> UserCenterNavHost(navController = userNavController)
            }
        }
    }
}
