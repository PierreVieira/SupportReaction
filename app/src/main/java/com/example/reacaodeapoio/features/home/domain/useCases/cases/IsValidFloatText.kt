package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

/**
 * Lógica utilizada para saber se uma string pode ser um número float válido */
class IsValidFloatText @Inject constructor() {
    companion object {
        private const val REGEX_EXPRESSION = "[+-]?([0-9]*[.])?[0-9]+" // https://cwi.com.br/blog/e-o-regex-como-vai/
    }

    /**
     * @param floatText texto a ser analisado se pode ser válido*/
    operator fun invoke(floatText: String): Boolean =
        REGEX_EXPRESSION.toPattern().matcher(floatText).matches()
}