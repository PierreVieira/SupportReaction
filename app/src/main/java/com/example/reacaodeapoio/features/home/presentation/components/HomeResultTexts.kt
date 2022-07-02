package com.example.reacaodeapoio.features.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.reacaodeapoio.ui.components.spacers.HorizontalSpacer

@Composable
fun HomeResultTexts(
    reactionA: String,
    reactionB: String,
    modifier: Modifier = Modifier,
) = Row(modifier = modifier.fillMaxSize()) {
    ResultTextField(
        modifier = Modifier.fillMaxSize(0.45f),
        value = reactionA,
        label = "RA"
    )
    HorizontalSpacer()
    ResultTextField(
        modifier = Modifier.fillMaxSize(),
        value = reactionB,
        label = "RB"
    )
}

@Composable
private fun ResultTextField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {},
        readOnly = true,
        label = {
            Text(text = label)
        }
    )
}