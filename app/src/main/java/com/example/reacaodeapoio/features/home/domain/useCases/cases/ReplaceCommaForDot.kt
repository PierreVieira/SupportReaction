package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

/**
 * Responsável por trocar vírgula por ponto */
class ReplaceCommaForDot @Inject constructor() {
    operator fun invoke(text: String) = text.replace(",", ".")
}