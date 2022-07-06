package com.example.reacaodeapoio.features.moreInfo.presentation

/**
 * Interface selada para descrever eventos de interface da tela de "Mais informações"
 * */
sealed interface MoreInfoUiEvent {
    object BackIconClick : MoreInfoUiEvent
}