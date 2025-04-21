package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import ie.setu.jetpack_android_shortvideo.model.Video

class HomeViewModel : ViewModel() {

    // 假資料（後續可從 AWS 取代）
    private val _videoList = mutableStateListOf(
        Video(
            id = "1",
            title = "This video is so funny",
            videoUrl = "https://example.com/video1.mp4",
            thumbnailUrl = "https://example.com/thumb1.jpg",
            likeCount = 11,
            comment = "User A"
        ),
        Video(
            id = "2",
            title = "This video is so funny",
            videoUrl = "https://example.com/video2.mp4",
            thumbnailUrl = "https://example.com/thumb2.jpg",
            likeCount = 5,
            comment = "User B"
        ),
        Video(
            id = "1",
            title = "This video is so funny",
            videoUrl = "https://example.com/video1.mp4",
            thumbnailUrl = "https://example.com/thumb1.jpg",
            likeCount = 11,
            comment = "User A"
        ),
        Video(
            id = "2",
            title = "This video is so funny",
            videoUrl = "https://example.com/video2.mp4",
            thumbnailUrl = "https://example.com/thumb2.jpg",
            likeCount = 5,
            comment = "User B"
        ),
        Video(
            id = "1",
            title = "This video is so funny",
            videoUrl = "https://example.com/video1.mp4",
            thumbnailUrl = "https://example.com/thumb1.jpg",
            likeCount = 11,
            comment = "User A"
        ),
        Video(
            id = "2",
            title = "This video is so funny",
            videoUrl = "https://example.com/video2.mp4",
            thumbnailUrl = "https://example.com/thumb2.jpg",
            likeCount = 5,
            comment = "User B"
        ),
        Video(
            id = "1",
            title = "This video is so funny",
            videoUrl = "https://example.com/video1.mp4",
            thumbnailUrl = "https://example.com/thumb1.jpg",
            likeCount = 11,
            comment = "User A"
        ),
        Video(
            id = "2",
            title = "This video is so funny",
            videoUrl = "https://example.com/video2.mp4",
            thumbnailUrl = "https://example.com/thumb2.jpg",
            likeCount = 5,
            comment = "User B"
        ),

    )

    val videoList: List<Video> = _videoList
}