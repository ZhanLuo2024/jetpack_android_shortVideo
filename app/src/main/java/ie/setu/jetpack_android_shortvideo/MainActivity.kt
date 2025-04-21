package ie.setu.jetpack_android_shortvideo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ie.setu.jetpack_android_shortvideo.tab.TabScaffold
import ie.setu.jetpack_android_shortvideo.ui.theme.Jetpack_android_shortVideoTheme
import ie.setu.jetpack_android_shortvideo.viewmodel.SharedUiViewModel

class MainActivity : ComponentActivity() {

    private val sharedUiViewModel: SharedUiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_android_shortVideoTheme {
                TabScaffold(sharedUiViewModel = sharedUiViewModel)
            }
        }
    }
}
