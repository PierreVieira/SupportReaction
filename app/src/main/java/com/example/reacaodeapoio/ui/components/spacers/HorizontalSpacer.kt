package com.example.reacaodeapoio.ui.components.spacers

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.example.reacaodeapoio.ui.components.spacers.base.SpacerType
import com.example.reacaodeapoio.ui.components.spacers.base.BaseSpacer

/**
 * Componente de espaçamento horizontal
 * @param size valor de espaçamento, por default é [null]
 * */
@Composable
fun HorizontalSpacer(size: Dp? = null) {
    BaseSpacer(
        spacerType = SpacerType.HORIZONTAL,
        size = size
    )
}