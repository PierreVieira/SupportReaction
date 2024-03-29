package com.example.reacaodeapoio.features.home.presentation.ui

import androidx.annotation.StringRes

/**
 * Interface selada para descrever eventos de interface da primeira tela
 * */
sealed interface HomeUiAction {
    data class ShowToast(@StringRes val message: Int): HomeUiAction
    object BackCursorToFirstField: HomeUiAction
    object NavigateToDownloads: HomeUiAction
    object NavigateToMoreOptions: HomeUiAction
}