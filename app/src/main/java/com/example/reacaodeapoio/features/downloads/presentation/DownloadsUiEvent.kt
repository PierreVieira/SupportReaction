package com.example.reacaodeapoio.features.downloads.presentation

import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher

sealed interface DownloadsUiEvent {
    object BackIconClick: DownloadsUiEvent
    data class Init(
        val context: Context,
    ): DownloadsUiEvent
}