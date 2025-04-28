package ie.setu.jetpack_android_shortvideo.model

data class Video(
    val video_id: String,
    val title: String,
    val video_url: String,
    val likes: Int,
    val created_at: Long,
    val views: Int
)
