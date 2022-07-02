package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

class ResultFormatter @Inject constructor() {
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
        return "$numberFormatted Newtons"
    }
}