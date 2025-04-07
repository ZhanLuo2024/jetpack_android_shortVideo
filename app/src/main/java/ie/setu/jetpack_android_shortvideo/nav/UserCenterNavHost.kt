package ie.setu.jetpack_android_shortvideo.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.jetpack_android_shortvideo.UserCenterScreen

@Composable
fun UserCenterNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "user_main") {
        composable("user_main") { UserCenterScreen() }
    }
}
