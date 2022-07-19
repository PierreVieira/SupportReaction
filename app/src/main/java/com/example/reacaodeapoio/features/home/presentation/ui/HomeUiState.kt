package com.example.reacaodeapoio.features.home.presentation.ui

/**
 * Classe responsável por armazenar o estado dos componentes da home
 * */
data class HomeUiState(
    val forceText: String,
    val firstDistanceText: String,
    val secondDistanceText: String,
    val reactionA: String,
    val reactionB: String,
    val isShowingForceReactions: Boolean,
    val isShowingClearResultsDialog: Boolean,
    val isShowingMoreMenu: Boolean
)
