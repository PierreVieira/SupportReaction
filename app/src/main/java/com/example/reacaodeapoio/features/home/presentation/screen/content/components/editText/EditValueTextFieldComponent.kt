package com.example.reacaodeapoio.features.home.presentation.screen.content.components.editText

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme

@Composable
fun EditValueTextField(
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

@Composable
@Preview(showBackground = true)
private fun EditValueTextFieldPreview() = SupportReactionTheme {
    EditValueTextField(
        label = "F (n)",
        value = "2.135",
        onValueChange = {}
    )
}