package ie.setu.jetpack_android_shortvideo.screen.publish

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ie.setu.jetpack_android_shortvideo.viewmodel.DiscoverViewModel
import ie.setu.jetpack_android_shortvideo.model.Video
import java.util.UUID

@Composable
fun PublishScreen(navController: NavController, discoverViewModel: DiscoverViewModel) {
    var title by remember { mutableStateOf("") }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // 拍照不處理，僅示意
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Enter video title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            launcher.launch(intent)
        }) {
            Text("Open Camera")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    discoverViewModel.videoList.add(
                        Video(
                            views = 0,
                            created_at = System.currentTimeMillis(),
                            video_url = "https://picsum.photos/400/240?random=${UUID.randomUUID()}",
                            likes = 0,
                            video_id = UUID.randomUUID().toString(),
                            title = title
                        )
                    )
                    navController.navigate("discover_main")
                }
            },
            enabled = title.isNotBlank()
        ) {
            Text("Upload")
        }
    }
}
