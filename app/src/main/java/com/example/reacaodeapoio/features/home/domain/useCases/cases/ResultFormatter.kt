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

    private val forbiddenSuffixes = listOf(COMMA, DOT)

    operator fun invoke(number: Float): String {
        var numberFormatted = String.format("%.3f", number)
        while (numberFormatted.last() == ZERO) {
            numberFormatted = numberFormatted.removeSuffix(ZERO.toString())
        }
        forbiddenSuffixes.forEach {
            if (numberFormatted.last() == it) {
                numberFormatted = numberFormatted.removeSuffix(it.toString())
            }
        }
        return "${replaceCommaForDot(numberFormatted)} Newtons"
    }
}