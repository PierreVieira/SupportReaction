package com.example.reacaodeapoio.features.downloads.presentation

import android.Manifest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reacaodeapoio.utils.permissionOk
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<DownloadsUiState>(DownloadsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<DownloadsUiAction>()
    val uiAction = _uiAction.asSharedFlow()

    fun dispatchUiEvent(event: DownloadsUiEvent) {
        when (event) {
            is DownloadsUiEvent.Init -> {
                if (
                    permissionOk(
                        event.context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {

                }
            }
            is DownloadsUiEvent.BackIconClick -> viewModelScope.launch {
                _uiAction.emit(DownloadsUiAction.BackToPreviousScreen)
            }
        }
    }
}