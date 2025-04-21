package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ie.setu.jetpack_android_shortvideo.model.Comment

class CommentViewModel : ViewModel() {
    val commentList = mutableStateListOf(
        Comment(
            id = "c001",
            user = "Emily",
            content = "This is amazing!",
            like = 5,
            imageUrl = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e"
        ),
        Comment(
            id = "c002",
            user = "John",
            content = "Haha I laughed so hard!",
            like = 3
        ),
        Comment(
            id = "c003",
            user = "Liam",
            content = "Where can I find this?",
            like = 1
        )
    )

    fun addComment(text: String) {
        val newComment = Comment(
            id = "c${commentList.size + 1}",
            user = "You",
            content = text,
            like = 0
        )
        commentList.add(0, newComment)
    }
}