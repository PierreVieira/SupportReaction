package com.example.reacaodeapoio.ui.components.spacers.base

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Componente de espaçamento
 * @param spacerType indica se é um espaçamento horizontal ou vertical
 * @param size indica o tamanho do espaçamento, se for nulo o valor será de 16.dp
 * */
@Composable
internal fun BaseSpacer(spacerType: SpacerType, size: Dp?) {
    val spaceSize = size ?: 16.dp
    Spacer(
        modifier = if (spacerType == SpacerType.HORIZONTAL) {
            Modifier.width(spaceSize)
        } else {
            Modifier.height(spaceSize)
        }
    )
}