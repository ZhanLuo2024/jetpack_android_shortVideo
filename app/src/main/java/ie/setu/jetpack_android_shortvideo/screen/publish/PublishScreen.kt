@file:Suppress("MISSING_DEPENDENCY_CLASS_IN_EXPRESSION_TYPE",
    "MISSING_DEPENDENCY_CLASS_IN_LAMBDA_PARAMETER"
)

package ie.setu.jetpack_android_shortvideo.screen.publish

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
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

    // 模擬調用相機
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // do nothing, just demo
        }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {

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

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (title.isNotBlank()) {
                discoverViewModel.videoList.add(0, Video(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    videoUrl = "https://example.com/fake.mp4",
                    thumbnailUrl = "https://placekitten.com/400/300",
                    likeCount = 0,
                    comment = "Uploaded via demo"
                ))
                navController.navigate("discover_main")
            }
        }) {
            Text("Upload")
        }
    }
}



