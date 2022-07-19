package com.example.reacaodeapoio.features.home.presentation.screen

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.reacaodeapoio.features.destinations.DownloadsScreenDestination
import com.example.reacaodeapoio.features.destinations.MoreInfoScreenDestination
import com.example.reacaodeapoio.features.home.presentation.HomeViewModel
import com.example.reacaodeapoio.features.home.presentation.screen.content.HomeScreenContent
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiAction
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

/**
 * Função compose responsável por desenhar a primeira tela
 * */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Destination(start = true)
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester.Default }
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = Unit) {
        viewModel.uiAction.collectLatest { action ->
            when (action) {
                is HomeUiAction.ShowToast -> Toast.makeText(
                    context,
                    context.getString(action.message),
                    Toast.LENGTH_SHORT
                ).show()
                is HomeUiAction.BackCursorToFirstField -> {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                }
                is HomeUiAction.NavigateToDownloads -> navigator.navigate(
                    DownloadsScreenDestination
                )
                is HomeUiAction.NavigateToMoreOptions -> navigator.navigate(
                    MoreInfoScreenDestination
                )
            }
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    HomeScreenContent(
        uiState = uiState,
        focusRequester = focusRequester,
        onForceChange = { force ->
            viewModel.dispatchUiEvent(HomeUiEvent.EnterWithForceText(force))
        },
        onFirstDistanceChange = { firstDistance ->
            viewModel.dispatchUiEvent(HomeUiEvent.EnterWithFirsDistanceText(firstDistance))
        },
        onSecondDistanceChange = { secondDistance ->
            viewModel.dispatchUiEvent(HomeUiEvent.EnterWithSecondDistanceText(secondDistance))
        },
        onCalculateClick = {
            viewModel.dispatchUiEvent(HomeUiEvent.CalculateButtonClick)
        },
        onClearResultsClick = {
            viewModel.dispatchUiEvent(HomeUiEvent.ClearResultsButtonClick)
        },
        onConfirmClearResults = {
            viewModel.dispatchUiEvent(HomeUiEvent.ConfirmClearResultsDialog)
        },
        onDismissConfirmClearResultsDialog = {
            viewModel.dispatchUiEvent(HomeUiEvent.DismissClearResultsDialog)
        },
        onCopyReportClick = {
            viewModel.dispatchUiEvent(HomeUiEvent.CopyReportResult)
        },
        onDownloadsClick = {
            viewModel.dispatchUiEvent(HomeUiEvent.DownloadsClick)
        },
        onDismissMenuOptions = {
            viewModel.dispatchUiEvent(HomeUiEvent.DismissMenuOptions)
        },
        onMoreInfoClick = {
            viewModel.dispatchUiEvent(HomeUiEvent.MoreInfoClick)
        },
        onMoreVertClick = {
            viewModel.dispatchUiEvent(HomeUiEvent.MoreVertClick)
        }
    )
}
