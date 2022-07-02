package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

class IsValidFloatText @Inject constructor() {
    companion object {
        private const val REGEX_EXPRESSION = "[+-]?([0-9]*[.])?[0-9]+"
    }

    operator fun invoke(floatText: String): Boolean =
        REGEX_EXPRESSION.toPattern().matcher(floatText).matches()
}