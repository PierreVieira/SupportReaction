package com.example.reacaodeapoio.features.downloads.presentation

/**
* Interface selada que informa o estado atual da tela (loading, success ou error)*/
sealed interface DownloadsUiState {
    object Loading : DownloadsUiState
    object Success : DownloadsUiState
    object Error : DownloadsUiState
}