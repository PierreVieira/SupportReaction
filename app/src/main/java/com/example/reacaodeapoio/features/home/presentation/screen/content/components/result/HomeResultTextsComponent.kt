package com.example.reacaodeapoio.features.home.presentation.screen.content.components.result

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reacaodeapoio.ui.components.spacers.HorizontalSpacer
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme

/**
 * Função compose responsável por desenhar o componente das caixas de texto do resultado RA e RB
 * @param reactionA valor calculado em RA
 * @param reactionB valor calculado em RB
 * @param modifier utilizado para modificações externas de layout */
@Composable
fun HomeResultTextsComponent(
    reactionA: String,
    reactionB: String,
    modifier: Modifier = Modifier,
) = Row(modifier = modifier.fillMaxWidth()) {
    ResultTextField(
        modifier = Modifier.fillMaxWidth(0.45f),
        value = reactionA,
        label = "RA"
    )
    HorizontalSpacer()
    ResultTextField(
        modifier = Modifier.fillMaxWidth(),
        value = reactionB,
        label = "RB"
    )
}

/**
 * Componente responsável por chamar uma caixa de texto genérica que não pode ser editada
 * @param label parâmetro que indica qual será a label da caixa de texto
 * @param value parâmetro que indica qual será o valor da caixa de texto*/
@Composable
private fun ResultTextField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) = OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = {},
    readOnly = true,
    label = {
        Text(text = label)
    }
)

@Composable
@Preview(showBackground = true)
fun HomeResultTextsComponentPreview() = SupportReactionTheme {
    HomeResultTextsComponent(
        reactionA = "1.512 Newtons",
        reactionB = "2.38 Newtons"
    )
}