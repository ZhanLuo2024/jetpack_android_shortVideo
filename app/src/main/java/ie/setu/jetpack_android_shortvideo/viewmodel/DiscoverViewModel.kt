package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import ie.setu.jetpack_android_shortvideo.model.Video

class DiscoverViewModel : ViewModel() {
    val videoList = mutableStateListOf(
        Video(
            id = "1",
            title = "Unexpected Dance Moves",
            videoUrl = "https://example.com/video3.mp4",
            thumbnailUrl = "https://picsum.photos/id/1005/400/300",
            likeCount = 315,
            comment = "This guy's crazy"
        )
    ).let { original ->
        mutableStateListOf<Video>().apply {
            repeat(10) { this += original } // repeat 10 times
        }
    }
}