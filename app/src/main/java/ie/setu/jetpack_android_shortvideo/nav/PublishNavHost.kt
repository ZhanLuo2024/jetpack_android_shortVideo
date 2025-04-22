package ie.setu.jetpack_android_shortvideo.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.jetpack_android_shortvideo.screen.publish.PublishScreen
import ie.setu.jetpack_android_shortvideo.viewmodel.DiscoverViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PublishNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "publish_main") {
        composable("publish_main") {
            val viewModel: DiscoverViewModel = viewModel()
            PublishScreen(
                navController = navController,
                discoverViewModel = viewModel
            )
        }
    }
}
