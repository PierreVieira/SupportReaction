package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

class CalculateForceReactionA @Inject constructor() {
    operator fun invoke(
        forceValue: Float,
        firstDistanceValue: Float,
        secondDistanceValue: Float,
    ): Float = (forceValue * firstDistanceValue) / (firstDistanceValue + secondDistanceValue) // Ra = (F * L1) / (L1 + L2)
}