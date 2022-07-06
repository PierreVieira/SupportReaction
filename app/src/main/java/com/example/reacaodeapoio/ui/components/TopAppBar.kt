package com.example.reacaodeapoio.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme

/**
 * Essa função implementa a barra que aparece no topo do aplicativo
 * @param textId id do texto que ficará no título da barra
 * @param actions função compose que contém os ícones em formato de linha dos ícones que indicam algum tipo de ação, por padrão é vazio
 * @param navigateBackAction função que irá indicar o que fazer caso o usuário clique no ícone de navegação, se esse parâmetro for nulo, o ícone de navegação não será mostrado, por padrão é [null]
 * */
@Composable
fun TopAppBar(
    @StringRes textId: Int,
    actions: @Composable RowScope.() -> Unit = {},
    navigateBackAction: (() -> Unit)? = null,
) = SmallTopAppBar(
    title = {
        Text(
            text = stringResource(id = textId),
            color = Color.White
        )
    },
    actions = actions,
    navigationIcon = {
        navigateBackAction?.let {
            IconButton(onClick = it) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.back_to_previous_screen)
                )
            }
        }
    },
    colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondary
    )
)

@Composable
@Preview
fun TopAppBarWithAllIconsPreview() = SupportReactionTheme {
    TopAppBar(
        textId = R.string.home_app_bar_title,
        navigateBackAction = {

        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
@Preview
fun TopAppBarWithOptionsIconPreview() = SupportReactionTheme {
    TopAppBar(
        textId = R.string.home_app_bar_title,
        navigateBackAction = {

        }
    )
}

@Composable
@Preview
fun TopAppBarWithMoreIconPreview() = SupportReactionTheme {
    TopAppBar(
        textId = R.string.home_app_bar_title,
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
@Preview
fun TopAppBarWithoutIconsPreview() = SupportReactionTheme {
    TopAppBar(textId = R.string.home_app_bar_title)
}
