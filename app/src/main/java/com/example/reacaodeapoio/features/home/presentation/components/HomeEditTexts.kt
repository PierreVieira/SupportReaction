package com.example.reacaodeapoio.features.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiState
import com.example.reacaodeapoio.ui.components.spacers.HorizontalSpacer

@Composable
fun HomeEditTexts(
    focusRequester: FocusRequester,
    uiState: HomeUiState,
    onForceChange: (String) -> Unit,
    onFirstDistanceChange: (String) -> Unit,
    onSecondDistanceChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        EditValueTextField(
            modifier = Modifier
                .fillMaxSize(0.28f)
                .focusRequester(focusRequester),
            label = "F",
            value = uiState.forceText,
            onValueChange = onForceChange
        )
        HorizontalSpacer()
        EditValueTextField(
            modifier = Modifier.fillMaxSize(0.45f),
            label = "L1",
            value = uiState.firstDistanceText,
            onValueChange = onFirstDistanceChange
        )
        HorizontalSpacer()
        EditValueTextField(
            modifier = Modifier.fillMaxSize(),
            label = "L2",
            value = uiState.secondDistanceText,
            onValueChange = onSecondDistanceChange
        )
    }
}

@Composable
private fun EditValueTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) = OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    label = {
        Text(text = label)
    },
    keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number
    )
)