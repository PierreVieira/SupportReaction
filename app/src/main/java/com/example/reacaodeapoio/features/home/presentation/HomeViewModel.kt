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
    ) // Primeiro estado da tela inicializando

    private var resultCache: ResultModel? = null // A cache (memória temporária) inicailiza em nulo

    private val _uiState = MutableStateFlow(firstState) // O estado da UI é inicializado por uma estrutura mutávei que começa com o valor do primeiro estado declarado anteriormente
    val uiState = _uiState.asStateFlow() // Declaração de uma variável de estado somente leitura

    private val _uiAction = MutableSharedFlow<HomeUiAction>() // O estado de ações é inicializado aqui
    val uiAction = _uiAction.asSharedFlow() // Inicialização de uma variável somente leitura para escutar as ações do view model para a tela

    fun dispatchUiEvent(event: HomeUiEvent) { // Função responsável por centralizar todos os eventos da tela
        when (event) { // Dependendo do evento
            is HomeUiEvent.EnterWithForceText -> _uiState.update { // Caso o usuário entre com o valor textual da força
                it.copy(forceText = replaceDotForComma(event.forceText)) // Atualize a informação do estado da forçã substituindo todos os pontos por vírgula
            }
            is HomeUiEvent.EnterWithFirsDistanceText -> _uiState.update { // Caso o usuário entre com o valor textual da primeira distância
                it.copy(firstDistanceText = replaceDotForComma(event.firstDistanceText)) // Substitua o valor textual da primeira distância no estado da ui
            }
            is HomeUiEvent.EnterWithSecondDistanceText -> _uiState.update { // Caso usuário entre com o valor textual da segunda distância
                it.copy(secondDistanceText = replaceDotForComma(event.secondDistanceText)) // Substitua o valor textual da segunda distância no estado da ui
            }
            is HomeUiEvent.CalculateButtonClick -> calculateButtonClick() // Caso o usuário clique no botão de calcular, chame a ação de calcular
            is HomeUiEvent.ClearResultsButtonClick -> _uiState.update { // Caso o usuário clique no botão de limpar resultados
                it.copy(isShowingClearResultsDialog = true) // Atualize o estado informando que está sendo mostrado o dialog de limpar resultado
            }
            is HomeUiEvent.DismissClearResultsDialog -> _uiState.update { // Caso o usuário clique fora do dialog de limpar resultados
                it.copy(isShowingClearResultsDialog = false) // Atualize o estado informando que não está sendo mostrado o dialog de limpar resultados
            }
            is HomeUiEvent.ConfirmClearResultsDialog -> { // Caso o usuário confirme que quer limpar os resultados
                _uiState.update { firstState } // Atualize o estado para o primeiro estado
                viewModelScope.launch {
                    _uiAction.emit(HomeUiAction.BackCursorToFirstField) // Informe à UI para mover o cursor para o primeiro campo de edição de texto
                }
            }
            is HomeUiEvent.CopyReportResult -> copyReportResult() // Caso o usuário clique para copiar o resultado, acioen a ação de copiar o resultado
            is HomeUiEvent.DownloadsClick -> viewModelScope.launch { // Caso o usuário clique para acessar a tela de downloads do app
                hideMoreMenu() // Esconda o menu de "mais"
                _uiAction.emit(HomeUiAction.NavigateToDownloads) // Informe à tela que queira ir à tela de "resultados de downloads"
            }
            is HomeUiEvent.MoreInfoClick -> viewModelScope.launch { // Caso o usuário clique para ir à tela de "mais informações"
                hideMoreMenu() // Esconda o menu de "mais"
                _uiAction.emit(HomeUiAction.NavigateToMoreOptions) // Informe à tela que deve-se navegar para a tela de "mais opções"
            }
            is HomeUiEvent.DismissMenuOptions -> hideMoreMenu() // Caso você dê dismiss no menu de opções, esconda esse menu
            is HomeUiEvent.MoreVertClick -> _uiState.update { // Caso clique nos 3 ícones verticais da tela principal
                it.copy(isShowingMoreMenu = true) // Atualize o estado informando que deve-se mostrar o menu de opções
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