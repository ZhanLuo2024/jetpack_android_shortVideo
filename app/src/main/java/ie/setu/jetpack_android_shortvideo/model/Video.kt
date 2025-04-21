package ie.setu.jetpack_android_shortvideo.model

data class Video(
    val id: String,
    val title: String,
    val videoUrl: String,
    val thumbnailUrl: String,
    val likeCount: Int,
    val comment: String
)