package com.example.reacaodeapoio.features.downloads.presentation

import com.example.reacaodeapoio.features.downloads.domain.ReportFileModel

sealed interface DownloadsUiState {
    object Loading: DownloadsUiState
    data class Success(val filesModel: List<ReportFileModel>): DownloadsUiState
    object Error: DownloadsUiState
}