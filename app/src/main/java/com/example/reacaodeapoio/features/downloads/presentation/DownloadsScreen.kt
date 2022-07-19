package com.example.reacaodeapoio.features.downloads.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.ui.components.TopAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun DownloadsScreen(
    navigator: DestinationsNavigator,
    viewModel: DownloadsViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.uiAction.collectLatest { action ->
            when (action) {
                is DownloadsUiAction.BackToPreviousScreen -> navigator.navigateUp()
            }
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                textId = R.string.downloads,
                navigateBackAction = { viewModel.dispatchUiEvent(DownloadsUiEvent.BackIconClick) }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            when (uiState) {
                is DownloadsUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
                is DownloadsUiState.Error -> Text(
                    text = stringResource(id = R.string.error_when_try_access_download)
                )
                is DownloadsUiState.Success -> {

                }
            }
        }
    }
}