package com.example.reacaodeapoio.features.home.presentation

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.reacaodeapoio.features.destinations.DownloadsScreenDestination
import com.example.reacaodeapoio.features.destinations.MoreInfoScreenDestination
import com.example.reacaodeapoio.features.home.presentation.components.HomeScreenContent
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiAction
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@Composable
@Destination(start = true)
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester.Default }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("HomeScreen", "PERMISSION GRANTED")

        } else {
            Log.d("HomeScreen", "PERMISSION DENIED")
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.uiAction.collectLatest { action ->
            when (action) {
                is HomeUiAction.ShowToast -> Toast.makeText(
                    context,
                    context.getString(action.message),
                    Toast.LENGTH_SHORT
                ).show()
                is HomeUiAction.BackCursorToFirstField -> focusRequester.requestFocus()
                is HomeUiAction.NavigateToDownloads -> navigator.navigate(
                    DownloadsScreenDestination()
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
        onForceChange = {
            viewModel.dispatchUiEvent(HomeUiEvent.EnterWithForceText(it))
        },
        onFirstDistanceChange = {
            viewModel.dispatchUiEvent(HomeUiEvent.EnterWithFirsDistanceText(it))
        },
        onSecondDistanceChange = {
            viewModel.dispatchUiEvent(HomeUiEvent.EnterWithSecondDistanceText(it))
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
        onDownloadReportClick = {
            Toast.makeText(
                context,
                "Relatório copiado para a aréa de transferência",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.dispatchUiEvent(
                HomeUiEvent.DownloadReportResult(
                    context = context,
                    launcher = launcher
                )
            )
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
