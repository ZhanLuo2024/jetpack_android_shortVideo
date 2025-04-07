package ie.setu.jetpack_android_shortvideo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun UserCenterScreen() {
    Box(
        Modifier.fillMaxSize().background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text("User Center Page", color = Color.White)
    }
}
