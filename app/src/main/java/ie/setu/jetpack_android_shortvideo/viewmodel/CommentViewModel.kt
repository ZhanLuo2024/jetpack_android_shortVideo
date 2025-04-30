package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ie.setu.jetpack_android_shortvideo.model.Comment
import ie.setu.jetpack_android_shortvideo.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class CommentUiState {
    object Loading : CommentUiState()
    data class Success(val comments: List<Comment>) : CommentUiState()
    data class Error(val message: String) : CommentUiState()
}

class CommentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CommentUiState>(CommentUiState.Loading)
    val uiState: StateFlow<CommentUiState> = _uiState

    init {
        fetchComments()
    }

    fun fetchComments() {
        viewModelScope.launch {
            _uiState.value = CommentUiState.Loading
            try {
                val comments = RetrofitClient.videoApiService.getComments()
                _uiState.value = CommentUiState.Success(comments)
            } catch (e: Exception) {
                _uiState.value = CommentUiState.Error("Failed to load comments.")
            }
        }
    }

    fun addComment(text: String) {
        val current = (_uiState.value as? CommentUiState.Success)?.comments ?: emptyList()
        val newComment = Comment(
            id = "c${current.size + 1}",
            user = "You",
            content = text,
            like = 0
        )
        _uiState.update {
            CommentUiState.Success(listOf(newComment) + current)
        }
    }
}
