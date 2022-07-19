package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

/**
 * Contém lógica para formatar os números informados pelo usuário
 * @param replaceCommaForDot lógica para trocar toda vírgula por ponto */
class ResultFormatter @Inject constructor(
    private val replaceCommaForDot: ReplaceCommaForDot
) {
    companion object {
        private const val COMMA = ','
        private const val DOT = '.'
        private const val ZERO = '0'
    }

    private val forbiddenSuffixes = listOf(COMMA, DOT) // Cria uma lista de caracteres proíbidos

    operator fun invoke(number: Float): String {
        var numberFormatted = String.format("%.3f", number) // formata o número recebido por parâmetro para uma string com apenas 3 casas decimais
        while (numberFormatted.last() == ZERO) { // Enquanto o último digito do número formatado for o caractere 0
            numberFormatted = numberFormatted.removeSuffix(ZERO.toString()) // O número formatado é ele mesmo com exceção do sufixo 0
        }
        forbiddenSuffixes.forEach { // Para cada caractere dos sufixos proíbidos
            if (numberFormatted.last() == it) { // Se o último caractere for o caractere atual
                numberFormatted = numberFormatted.removeSuffix(it.toString()) // O número formatado é ele mesmo com exceção do sufixo atual
            }
        }
        return "${replaceCommaForDot(numberFormatted)} Newtons" // Retorne o número formatado substituindo todas as vírgulas por ponto com o acréscimo da palavra "Newtons"
    }
}