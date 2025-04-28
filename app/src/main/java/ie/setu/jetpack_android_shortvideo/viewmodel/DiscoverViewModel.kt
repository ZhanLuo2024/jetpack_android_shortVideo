package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import ie.setu.jetpack_android_shortvideo.model.Video

class DiscoverViewModel : ViewModel() {
    val videoList = mutableStateListOf(
        Video(
            views = 315,
            created_at = System.currentTimeMillis(),
            video_url = "https://example.com/video3.mp4",
            likes = 120,
            video_id = "video_demo_1",
            title = "Unexpected Dance Moves"
        )
    ).let { original ->
        mutableStateListOf<Video>().apply {
            repeat(10) { this += original }
        }
    }
}
