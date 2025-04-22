package ie.setu.jetpack_android_shortvideo.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.jetpack_android_shortvideo.screen.comment.CommentScreen
import ie.setu.jetpack_android_shortvideo.screen.discover.DiscoverScreen
import ie.setu.jetpack_android_shortvideo.screen.discover.PreviewScreen
import ie.setu.jetpack_android_shortvideo.viewmodel.SharedUiViewModel

@Composable
fun DiscoverNavHost(
    navController: NavHostController,
    sharedUiViewModel: SharedUiViewModel
) {
    NavHost(navController = navController, startDestination = "discover_main") {
        composable("discover_main") {
            DiscoverScreen(navController = navController)
        }

        composable("preview/{videoId}") { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId")
            val sharedUiViewModel: SharedUiViewModel = viewModel(backStackEntry)

            PreviewScreen(
                navController = navController,
                sharedUiViewModel = sharedUiViewModel,
                videoId = videoId
            )
        }


        composable("comment") {
            CommentScreen(sharedUiViewModel = sharedUiViewModel)
        }

    }
}

