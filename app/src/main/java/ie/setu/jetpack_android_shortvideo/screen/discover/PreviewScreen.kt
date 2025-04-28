package ie.setu.jetpack_android_shortvideo.screen.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import ie.setu.jetpack_android_shortvideo.viewmodel.DiscoverViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import ie.setu.jetpack_android_shortvideo.viewmodel.SharedUiViewModel

@Composable
fun PreviewScreen(
    navController: NavController,
    sharedUiViewModel: SharedUiViewModel,
    videoId: String?
) {
    val viewModel: DiscoverViewModel = viewModel()
    val video = viewModel.videoList.find { it.video_id == videoId }

    // 修正為穩定且可正確觸發的 hide 操作
    LaunchedEffect(videoId) {
        sharedUiViewModel.hideTab()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = video?.title ?: "Unknown",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Image(
            painter = rememberAsyncImagePainter(video?.video_url ?: ""),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "❤️ ${video?.likes ?: 0} likes",
            fontSize = 16.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate("comment")
        }) {
            Text("View Comments")
        }
    }
}
