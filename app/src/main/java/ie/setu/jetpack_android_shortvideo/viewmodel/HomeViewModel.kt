package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ie.setu.jetpack_android_shortvideo.model.Video
import ie.setu.jetpack_android_shortvideo.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class VideoUiState {
    object Loading : VideoUiState()
    data class Success(val videos: List<Video>) : VideoUiState()
    data class Error(val message: String) : VideoUiState()
}

class HomeViewModel : ViewModel() {

    private val repository = VideoRepository()

    private val _uiState = MutableStateFlow<VideoUiState>(VideoUiState.Loading)
    val uiState: StateFlow<VideoUiState> = _uiState

    init {
        fetchVideos()
    }

    fun fetchVideos() {
        _uiState.value = VideoUiState.Loading
        viewModelScope.launch {
            try {
                val videos = repository.getVideos()
                _uiState.value = VideoUiState.Success(videos)
            } catch (e: Exception) {
                _uiState.value = VideoUiState.Error("Failed to load videos. Please try again.")
            }
        }
    }
}
