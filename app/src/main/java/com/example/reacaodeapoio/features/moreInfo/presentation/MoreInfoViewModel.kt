package com.example.reacaodeapoio.features.moreInfo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * View Model que controla a tela de "Mais informações"
 * */
class MoreInfoViewModel : ViewModel() {

    private val _uiAction = MutableSharedFlow<MoreInfoUiAction>()
    val uiAction = _uiAction.asSharedFlow()

    fun dispatchUiEvent(event: MoreInfoUiEvent) {
        when (event) {
            is MoreInfoUiEvent.BackIconClick -> viewModelScope.launch {
                _uiAction.emit(MoreInfoUiAction.BackToPreviousScreen)
            }
        }
    }
}