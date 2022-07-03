package com.example.reacaodeapoio.features.home.presentation.screen.content.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme

@Composable
fun ConfirmClearDialogComponent(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) = AlertDialog(
    onDismissRequest = onDismissRequest,
    title = {
        Text(text = stringResource(id = R.string.clear_results_dialog_title))
    },
    text = {
        Text(text = stringResource(id = R.string.clear_results_dialog_text))
    },
    confirmButton = {
        Button(onClick = onConfirm) {
            Text(text = stringResource(id = R.string.ok))
        }
    },
    dismissButton = {
        Button(onClick = onDismissRequest) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }
)

@Composable
@Preview
fun ConfirmClearDialogComponentPreview() = SupportReactionTheme {
    ConfirmClearDialogComponent(
        onConfirm = {},
        onDismissRequest = {}
    )
}