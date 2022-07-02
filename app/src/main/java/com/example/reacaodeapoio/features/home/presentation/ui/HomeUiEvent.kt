package com.example.reacaodeapoio.features.home.presentation.ui

import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher

sealed interface HomeUiEvent {
    data class EnterWithForceText(val forceText: String) : HomeUiEvent
    data class EnterWithFirsDistanceText(val firstDistanceText: String) : HomeUiEvent
    data class EnterWithSecondDistanceText(val secondDistanceText: String) : HomeUiEvent

    data class DownloadReportResult(
        val context: Context,
        val launcher: ManagedActivityResultLauncher<String, Boolean>,
    ) : HomeUiEvent

    object CalculateButtonClick : HomeUiEvent
    object ClearResultsButtonClick : HomeUiEvent
    object DismissClearResultsDialog : HomeUiEvent
    object ConfirmClearResultsDialog : HomeUiEvent
    object MoreVertClick : HomeUiEvent
    object DownloadsClick : HomeUiEvent
    object MoreInfoClick : HomeUiEvent
    object DismissMenuOptions : HomeUiEvent
}