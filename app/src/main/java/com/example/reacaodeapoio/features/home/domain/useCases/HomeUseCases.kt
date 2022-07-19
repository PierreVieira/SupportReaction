package com.example.reacaodeapoio.features.home.domain.useCases

import com.example.reacaodeapoio.features.home.domain.useCases.cases.*

/**
 * Classe responsável por encapsular todas as lógicas principais do app */
data class HomeUseCases(
    val calculateForceReactionA: CalculateForceReactionA, // Caso de uso para calcular a força de reação em A
    val calculateForceReactionB: CalculateForceReactionB, // Caso de uso para calcular a força de reação em B
    val isValidText: IsValidFloatText, // Caso de uso para verificar se um texto é válido ou não
    val resultFormatter: ResultFormatter, // Caso de uso para formatar um número que possa ter casas decimais
    val copyReportToClipBoard: CopyReportToClipBoard, // Caso de uso para copiar o relatório para o teclado
    val replaceCommaForDot: ReplaceCommaForDot // Caso de uso para substituir vírgula por ponto
)