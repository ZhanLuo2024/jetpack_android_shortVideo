package ie.setu.jetpack_android_shortvideo.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ie.setu.jetpack_android_shortvideo.screen.usercenter.UserCenterScreen
import ie.setu.jetpack_android_shortvideo.viewmodel.DiscoverViewModel
import ie.setu.jetpack_android_shortvideo.viewmodel.SharedUiViewModel

@Composable
fun UserCenterNavHost(
    navController: NavHostController,
    discoverViewModel: DiscoverViewModel,
    sharedUiViewModel: SharedUiViewModel
) {
    NavHost(navController = navController, startDestination = "user_center_main") {
        composable("user_center_main") {
            UserCenterScreen(
                discoverViewModel = discoverViewModel,
                sharedUiViewModel = sharedUiViewModel
            )
        }
    }
}
