package ie.setu.jetpack_android_shortvideo.model

data class Comment(
    val id: String,
    val user: String,
    val content: String,
    val like: Int,
    val imageUrl: String? = null
)