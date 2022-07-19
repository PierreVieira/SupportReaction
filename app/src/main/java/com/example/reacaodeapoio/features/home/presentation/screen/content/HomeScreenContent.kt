package com.example.reacaodeapoio.features.home.presentation.screen.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.features.home.presentation.screen.content.components.ConfirmClearDialogComponent
import com.example.reacaodeapoio.features.home.presentation.screen.content.components.editText.HomeEditTextsComponent
import com.example.reacaodeapoio.features.home.presentation.screen.content.components.HomeOptionsDropdownMenuComponent
import com.example.reacaodeapoio.features.home.presentation.ui.HomeUiState
import com.example.reacaodeapoio.features.home.presentation.screen.content.components.GrayDividerComponent
import com.example.reacaodeapoio.features.home.presentation.screen.content.components.result.ForceReactionResultsComponent
import com.example.reacaodeapoio.ui.components.TopAppBar
import com.example.reacaodeapoio.ui.components.spacers.VerticalSpacer
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme

/**
 * Essa função implementa o conteúdo da tela principal do app
 * @param uiState guarda o estado atual da tela
 * @param focusRequester controla o estado do teclado (fazendo sumir e aparecer)
 * @param onForceChange função que informa o que irá acontecer quando o usuário mudar o valor de F (força)
 * @param onFirstDistanceChange função que informa o que irá acontecer quando o usuário mudar o valor de L1 (primeira distância)
 * @param onSecondDistanceChange função que informa o que irá acontecer quando o usuário mudar o valor de L2 (segunda distância)
 * @param onCalculateClick função que informa o que riá acontecer quando o usuário clicar no botão de calcular
 * @param onClearResultsClick função que informa o que irá acontecer quando o usuário clicar no botão de limpar os resultados na tela
 * @param onConfirmClearResults função que informa o que irá acontecer quando o usuário confirmar que quer limpar os resultados
 * @param onDismissConfirmClearResultsDialog função que informa o que irá acontecer qunado o usuário clicar fora do dialog de confirmação de limpeza de resultado
 * @param onCopyReportClick função que informa o que irá acontecer quando o usuário clicar no botão de copiar o relatório
 * @param onMoreVertClick função que informa o que irá acontecer quando o usuário clicar no ícone de 3 pontinhos verticais na app bar
 * @param onDownloadsClick função que informa o que irá acontecer qunado o usuário clicar na opção de "Downloads" do menu que abre ao clicar nas 3 bolinhas verticais da app bar
 * @param onMoreInfoClick função que informa o que irá acontecer quando o usuário clicar na opção de "Informações" do menu que abre ao clicar nas 3 bolinhas verticais da app bar*/

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    focusRequester: FocusRequester,
    onForceChange: (String) -> Unit,
    onFirstDistanceChange: (String) -> Unit,
    onSecondDistanceChange: (String) -> Unit,
    onCalculateClick: () -> Unit,
    onClearResultsClick: () -> Unit,
    onConfirmClearResults: () -> Unit,
    onDismissConfirmClearResultsDialog: () -> Unit,
    onCopyReportClick: () -> Unit,
    onMoreVertClick: () -> Unit,
    onDismissMenuOptions: () -> Unit,
    onDownloadsClick: () -> Unit,
    onMoreInfoClick: () -> Unit,
) {
    if (uiState.isShowingClearResultsDialog) {
        ConfirmClearDialogComponent(
            onConfirm = onConfirmClearResults,
            onDismissRequest = onDismissConfirmClearResultsDialog
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                textId = R.string.home_app_bar_title,
                actions = {
                    IconButton(onClick = onMoreVertClick) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(id = R.string.options),
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        HomeOptionsDropdownMenuComponent(
            expanded = uiState.isShowingMoreMenu,
            onDismiss = onDismissMenuOptions,
            onDownloadsClick = onDownloadsClick,
            onMoreInfoClick = onMoreInfoClick,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(
                    horizontal = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                VerticalSpacer()
            }
            item {
                Text(
                    text = stringResource(id = R.string.calculation_of_support_reactions),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                AnimatedContent(targetState = uiState.isShowingForceReactions) { showingResult ->
                    if (showingResult.not()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            VerticalSpacer(8.dp)
                            GrayDividerComponent()
                            VerticalSpacer()
                            Image(
                                painter = painterResource(
                                    id = R.drawable.viga
                                ),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            item {
                VerticalSpacer(8.dp)
            }
            item {
                GrayDividerComponent()
            }
            item {
                VerticalSpacer()
            }
            item {
                HomeEditTextsComponent(
                    uiState = uiState,
                    onForceChange = onForceChange,
                    onFirstDistanceChange = onFirstDistanceChange,
                    onSecondDistanceChange = onSecondDistanceChange,
                    focusRequester = focusRequester
                )
            }
            item {
                VerticalSpacer()
            }
            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCalculateClick,
                ) {
                    Text(text = stringResource(id = R.string.calculate_text_button))
                }
            }
            item {
                VerticalSpacer(12.dp)
            }
            item {
                AnimatedContent(uiState.isShowingForceReactions) { canShow ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        if (canShow) {
                            ForceReactionResultsComponent(
                                reactionA = uiState.reactionA,
                                reactionB = uiState.reactionB,
                                onCopyReportClick = onCopyReportClick,
                                onClearResultsClick = onClearResultsClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun HomeScreenContentPreview() = SupportReactionTheme {
    HomeScreenContent(
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
        focusRequester = FocusRequester(),
        onForceChange = {},
        onFirstDistanceChange = {},
        onSecondDistanceChange = {},
        onCalculateClick = {},
        onClearResultsClick = {},
        onConfirmClearResults = {},
        onDismissConfirmClearResultsDialog = {},
        onCopyReportClick = {},
        onMoreVertClick = {},
        onDismissMenuOptions = {},
        onDownloadsClick = {},
        onMoreInfoClick = {}
    )
}