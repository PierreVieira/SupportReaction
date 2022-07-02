package com.example.reacaodeapoio.features.home.presentation.ui

import androidx.annotation.StringRes

sealed interface HomeUiAction {
    data class ShowToast(@StringRes val message: Int): HomeUiAction
    object BackCursorToFirstField: HomeUiAction
    object NavigateToDownloads: HomeUiAction
    object NavigateToMoreOptions: HomeUiAction
}