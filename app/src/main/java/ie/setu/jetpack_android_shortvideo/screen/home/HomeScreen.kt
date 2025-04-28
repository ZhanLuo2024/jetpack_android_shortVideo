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

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is VideoUiState.Loading -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Loading videos...")
                }
            }
            is VideoUiState.Success -> {
                val videos = (uiState as VideoUiState.Success).videos
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(videos) { video ->
                        VideoCard(
                            title = video.title,
                            likeCount = video.likes,
                            navController = navController
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
            is VideoUiState.Error -> {
                val errorMessage = (uiState as VideoUiState.Error).message
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = errorMessage)
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { viewModel.fetchVideos() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}
