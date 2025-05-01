// SharedUiViewModel.kt
package ie.setu.jetpack_android_shortvideo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.mutableStateOf

class SharedUiViewModel : ViewModel() {
    private val _isTabVisible = MutableStateFlow(true)
    val isTabVisible: StateFlow<Boolean> = _isTabVisible

    // Tab-bar status
    fun showTab() {
        _isTabVisible.value = true
    }

    fun hideTab() {
        _isTabVisible.value = false
    }

    // login status
    var isLoggedIn = mutableStateOf(false)
    var loggedInEmail = mutableStateOf<String?>(null)

    fun login(email: String) {
        isLoggedIn.value = true
        loggedInEmail.value = email
    }

    fun logout() {
        isLoggedIn.value = false
        loggedInEmail.value = null
    }
}
