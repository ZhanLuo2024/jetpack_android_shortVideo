package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.lifecycle.ViewModel
import ie.setu.jetpack_android_shortvideo.model.Video
import androidx.compose.runtime.mutableStateListOf

class DiscoverViewModel : ViewModel() {

    // ✅ 改為可觀察、可修改的 Compose 狀態列表
    var videoList = mutableStateListOf<Video>()
        private set

    init {
        // 初始化假資料
        videoList.addAll(
            listOf(
                Video(
                    title = "Epic Dance Moves",
                    video_id = "d1",
                    video_url = "https://picsum.photos/id/1011/400/240",
                    likes = 12,
                    created_at = 10,
                    views = 100
                ),
                Video(
                    title = "Funny Cat Compilation",
                    video_id = "d2",
                    video_url = "https://picsum.photos/id/1025/400/240",
                    likes = 8,
                    created_at = 10,
                    views = 80
                ),
                Video(
                    title = "Street Performance",
                    video_id = "d3",
                    video_url = "https://picsum.photos/id/1043/400/240",
                    likes = 22,
                    created_at = 10,
                    views = 210
                ),
                Video(
                    title = "Travel Vlog",
                    video_id = "d4",
                    video_url = "https://picsum.photos/id/1062/400/240",
                    likes = 5,
                    created_at = 10,
                    views = 45
                ),
                Video(
                    title = "Amazing Goal",
                    video_id = "d5",
                    video_url = "https://picsum.photos/id/1076/400/240",
                    likes = 33,
                    created_at = 10,
                    views = 340
                ),
                Video(
                    title = "Comedy Skit",
                    video_id = "d6",
                    video_url = "https://picsum.photos/id/1084/400/240",
                    likes = 14,
                    created_at = 10,
                    views = 123
                )
            )
        )
    }
}
