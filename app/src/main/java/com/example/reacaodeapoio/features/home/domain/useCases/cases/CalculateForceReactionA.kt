package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

/**
 * Cálculo da força de reação em A */
class CalculateForceReactionA @Inject constructor() {

    /**
     * @param forceValue valor da força aplicada
     * @param firstDistanceValue valor da primeira distância
     * @param secondDistanceValue valor da segunda distância */
    operator fun invoke(
        forceValue: Float,
        firstDistanceValue: Float,
        secondDistanceValue: Float,
    ): Float = (forceValue * firstDistanceValue) / (firstDistanceValue + secondDistanceValue)
}