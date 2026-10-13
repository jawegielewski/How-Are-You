package pl.jawegiel.howareyou.ui.main.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import pl.jawegiel.howareyou.ui.main.MainViewModel
import pl.jawegiel.howareyou.ui.main.components.PostAdditionFloatingActionButton
import pl.jawegiel.howareyou.ui.main.components.PosterBottomSheet
import pl.jawegiel.howareyou.ui.main.components.PostsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    val bottomSheetText by viewModel.bottomSheetText.collectAsState()
    val posts by viewModel.postsState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            PostAdditionFloatingActionButton(onClick = {
                showBottomSheet = true
            })
        }) { contentPadding ->

        PostsList(posts, Modifier.fillMaxSize(), contentPadding)

        if (showBottomSheet) {
            PosterBottomSheet(
                textValue = bottomSheetText,
                onTextChange = { viewModel.onTextChange(it) },
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false },
                onHideClick = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            viewModel.submitData()
                            showBottomSheet = false
                        }
                    }
                })
        }
    }
}