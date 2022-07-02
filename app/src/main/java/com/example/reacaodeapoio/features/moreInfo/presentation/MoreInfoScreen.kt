package com.example.reacaodeapoio.features.moreInfo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun MoreInfoScreen(
    navigator: DestinationsNavigator,
    viewModel: MoreInfoViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.uiAction.collectLatest { action ->
            when (action) {
                is MoreInfoUiAction.BackToPreviousScreen -> navigator.navigateUp()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                textId = R.string.infos,
                navigateBackAction = {
                    viewModel.dispatchUiEvent(MoreInfoUiEvent.BackIconClick)
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            Text(
                text = stringResource(id = R.string.nothing_there),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}