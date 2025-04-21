package ie.setu.jetpack_android_shortvideo.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.jetpack_android_shortvideo.screen.home.HomeScreen
import ie.setu.jetpack_android_shortvideo.screen.comment.CommentScreen
import ie.setu.jetpack_android_shortvideo.viewmodel.SharedUiViewModel

@Composable
fun HomeNavHost(
    navController: NavHostController,
    sharedUiViewModel: SharedUiViewModel
) {
    NavHost(navController = navController, startDestination = "home_main") {
        composable("home_main") {
            HomeScreen(navController = navController)
        }
        composable("comment") {
            CommentScreen(sharedUiViewModel = sharedUiViewModel)
        }
    }
}
