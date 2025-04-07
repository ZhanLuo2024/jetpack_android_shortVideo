package ie.setu.jetpack_android_shortvideo.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.jetpack_android_shortvideo.DiscoverScreen

@Composable
fun DiscoverNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "discover_main") {
        composable("discover_main") { DiscoverScreen() }
    }
}
