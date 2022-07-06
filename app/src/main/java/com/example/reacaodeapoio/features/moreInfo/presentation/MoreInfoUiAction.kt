package com.example.reacaodeapoio.features.moreInfo.presentation

/**
 * Interface selada para descrever ações de interface da tela de "Mais informações"
 * */
sealed interface MoreInfoUiAction {
    object BackToPreviousScreen: MoreInfoUiAction
}