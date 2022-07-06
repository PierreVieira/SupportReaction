package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

/**
 * Cálculo da força de reação em B */
class CalculateForceReactionB @Inject constructor() {

    /**
     * @param forceValue valor da força aplicada
     * @param forceReactionAValue valor da força de reação em A */
    operator fun invoke(
        forceValue: Float,
        forceReactionAValue: Float
    ) = forceValue - forceReactionAValue
}