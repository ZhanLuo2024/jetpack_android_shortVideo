package ie.setu.jetpack_android_shortvideo.model

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("comment_id")
    val id: String,

    val user: String,

    @SerializedName("text")
    val content: String,

    val like: Int = 0,  // 後端沒給，但保留欄位不出錯

    val imageUrl: String? = null  // 後端沒給，也保留為 null
)
