package ie.setu.jetpack_android_shortvideo.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.jetpack_android_shortvideo.screen.home.HomeScreen

@Composable
fun HomeNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "home_main") {
        composable("home_main") { HomeScreen() }
    }
}
