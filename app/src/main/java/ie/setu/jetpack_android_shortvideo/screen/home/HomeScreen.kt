package ie.setu.jetpack_android_shortvideo.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ie.setu.jetpack_android_shortvideo.viewmodel.HomeViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val videos = viewModel.videoList

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(videos) { video ->
            VideoCard(
                title = video.title,
                comment = video.comment,
                likeCount = video.likeCount
            )
        }
    }
}

