package com.example.reacaodeapoio.features.home.presentation.screen.content.components

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme

/**
 * Componente responsável por desenhar uma linha divisória cinza
 * @param modifier utilizado para modificações externas de layout
 * */
@Composable
fun GrayDividerComponent(modifier: Modifier = Modifier) = Divider(
    modifier = modifier,
    color = Color.LightGray
)

@Composable
@Preview
fun GrayDividerComponentPreview() = SupportReactionTheme {
    GrayDividerComponent()
}