package com.example.reacaodeapoio.features.home.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.features.home.domain.model.ResultModel
import com.example.reacaodeapoio.features.home.domain.useCases.HomeUseCases
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiAction
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiEvent
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model responsável por fazer o controle lógico da home (primeira tela)
 * @param homeUseCases contém todos os casos de uso da home
 * */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
) : ViewModel() {

    private val firstState = HomeUiState(
        forceText = "",
        firstDistanceText = "",
        secondDistanceText = "",
        reactionA = "",
        reactionB = "",
        isShowingForceReactions = false,
        isShowingClearResultsDialog = false,
        isShowingMoreMenu = false
    )

    private var resultCache: ResultModel? = null

    private val _uiState = MutableStateFlow(firstState)
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<HomeUiAction>()
    val uiAction = _uiAction.asSharedFlow()

    fun dispatchUiEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.EnterWithForceText -> _uiState.update {
                it.copy(forceText = replaceDotForComma(event.forceText))
            }
            is HomeUiEvent.EnterWithFirsDistanceText -> _uiState.update {
                it.copy(firstDistanceText = replaceDotForComma(event.firstDistanceText))
            }
            is HomeUiEvent.EnterWithSecondDistanceText -> _uiState.update {
                it.copy(secondDistanceText = replaceDotForComma(event.secondDistanceText))
            }
            is HomeUiEvent.CalculateButtonClick -> calculateButtonClick()
            is HomeUiEvent.ClearResultsButtonClick -> _uiState.update {
                it.copy(isShowingClearResultsDialog = true)
            }
            is HomeUiEvent.DismissClearResultsDialog -> _uiState.update {
                it.copy(isShowingClearResultsDialog = false)
            }
            is HomeUiEvent.ConfirmClearResultsDialog -> {
                _uiState.update { firstState }
                viewModelScope.launch {
                    _uiAction.emit(HomeUiAction.BackCursorToFirstField)
                }
            }
            is HomeUiEvent.CopyReportResult -> copyReportResult()
            is HomeUiEvent.DownloadsClick -> viewModelScope.launch {
                hideMoreMenu()
                _uiAction.emit(HomeUiAction.NavigateToDownloads)
            }
            is HomeUiEvent.MoreInfoClick -> viewModelScope.launch {
                hideMoreMenu()
                _uiAction.emit(HomeUiAction.NavigateToMoreOptions)
            }
            is HomeUiEvent.DismissMenuOptions -> hideMoreMenu()
            is HomeUiEvent.MoreVertClick -> _uiState.update {
                it.copy(isShowingMoreMenu = true)
            }
        }
    }

    private fun hideMoreMenu() {
        _uiState.update { it.copy(isShowingMoreMenu = false) }
    }

    private fun copyReportResult() {
        resultCache?.let { resultModel ->
            homeUseCases.copyReportToClipBoard(resultModel)
            showToast(R.string.result_copied_to_clipboard)
        } ?: showToast(R.string.error_when_try_download)
    }

    private fun calculateButtonClick() {
        when {
            homeUseCases.isValidText(uiState.value.forceText).not() ->
                showToast(R.string.force_text_wrong)
            homeUseCases.isValidText(uiState.value.firstDistanceText).not() ->
                showToast(R.string.first_distance_text_wrong)
            homeUseCases.isValidText(uiState.value.secondDistanceText).not() ->
                showToast(R.string.second_distance_text_wrong)
            else -> calculateReactionForces()
        }
    }

    private fun showToast(@StringRes message: Int) {
        viewModelScope.launch {
            _uiAction.emit(HomeUiAction.ShowToast(message))
        }
    }

    private fun calculateReactionForces() {
        val forceValue = uiState.value.forceText.toFloat()
        val reactionA = homeUseCases.calculateForceReactionA(
            forceValue = forceValue,
            firstDistanceValue = uiState.value.firstDistanceText.toFloat(),
            secondDistanceValue = uiState.value.secondDistanceText.toFloat()
        )
        val reactionB = homeUseCases.calculateForceReactionB(
            forceValue = forceValue,
            forceReactionAValue = reactionA
        )
        _uiState.update {
            it.copy(
                reactionA = homeUseCases.resultFormatter(reactionA),
                reactionB = homeUseCases.resultFormatter(reactionB),
                isShowingForceReactions = true
            )
        }
        uiState.value.apply {
            resultCache = ResultModel(
                forceText = forceText,
                firstDistanceText = firstDistanceText,
                secondDistanceText = secondDistanceText,
                reactionA = homeUseCases.resultFormatter(reactionA),
                reactionB = homeUseCases.resultFormatter(reactionB)
            )
        }
    }

    private fun replaceDotForComma(text: String) = homeUseCases.replaceCommaForDot(text)
}