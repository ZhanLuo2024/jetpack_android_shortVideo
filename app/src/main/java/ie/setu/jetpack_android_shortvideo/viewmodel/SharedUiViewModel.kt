// SharedUiViewModel.kt
package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedUiViewModel : ViewModel() {
    private val _isTabVisible = MutableStateFlow(true)
    val isTabVisible: StateFlow<Boolean> = _isTabVisible

    fun showTab() {
        _isTabVisible.value = true
    }

    fun hideTab() {
        _isTabVisible.value = false
    }
}
