package com.example.reacaodeapoio.features.home.presentation.screen.content.components.editText

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.features.home.presentation.screen.content.components.editText.EditValueTextField
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiState
import com.example.reacaodeapoio.ui.components.spacers.HorizontalSpacer
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme

@Composable
fun HomeEditTextsComponent(
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
                .fillMaxWidth(0.28f)
                .focusRequester(focusRequester),
            label = stringResource(id = R.string.input_force_label),
            value = uiState.forceText,
            onValueChange = onForceChange
        )
        HorizontalSpacer()
        EditValueTextField(
            modifier = Modifier.fillMaxWidth(0.45f),
            label = stringResource(id = R.string.input_first_distance_label),
            value = uiState.firstDistanceText,
            onValueChange = onFirstDistanceChange
        )
        HorizontalSpacer()
        EditValueTextField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(id = R.string.input_second_distance_label),
            value = uiState.secondDistanceText,
            onValueChange = onSecondDistanceChange
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeEditTextsComponentPreview() = SupportReactionTheme {
    HomeEditTextsComponent(
        focusRequester = FocusRequester(),
        uiState = HomeUiState(
            forceText = "",
            firstDistanceText = "",
            secondDistanceText = "",
            reactionA = "",
            reactionB = "",
            isShowingForceReactions = false,
            isShowingClearResultsDialog = false,
            isShowingMoreMenu = false
        ),
        onForceChange = {},
        onFirstDistanceChange = {},
        onSecondDistanceChange = {}
    )
}