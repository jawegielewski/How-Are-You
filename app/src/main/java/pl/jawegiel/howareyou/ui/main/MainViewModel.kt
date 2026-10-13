package pl.jawegiel.howareyou.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pl.jawegiel.howareyou.data.model.Post
import pl.jawegiel.howareyou.domain.repository.PostRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val _bottomSheetText = MutableStateFlow("")
    val bottomSheetText: StateFlow<String> = _bottomSheetText.asStateFlow()

    val postsState: StateFlow<List<Post>> = repository.getPosts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onTextChange(newText: String) {
        _bottomSheetText.value = newText
    }

    fun submitData() {
        val dataToSave = _bottomSheetText.value
        if (dataToSave.isNotBlank()) {
            addPost(_bottomSheetText.value)
            clearText()
        }
    }

    fun clearText() {
        _bottomSheetText.value = ""
    }

    fun addPost(name: String) {
        viewModelScope.launch {
            repository.addPost(name)
        }
    }
}