package ie.setu.jetpack_android_shortvideo.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ie.setu.jetpack_android_shortvideo.viewmodel.HomeViewModel
import ie.setu.jetpack_android_shortvideo.viewmodel.VideoUiState

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is VideoUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is VideoUiState.Error -> {
            val errorMessage = (uiState as VideoUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
        is VideoUiState.Success -> {
            val videos = (uiState as VideoUiState.Success).videos

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(videos) { video ->
                    VideoCard(
                        title = video.title,
                        likeCount = video.likes,
                        videoUrl = video.video_url,
                        navController = navController,
                        videoId = video.video_id,
                    )
                }
            }
        }
    }
}
