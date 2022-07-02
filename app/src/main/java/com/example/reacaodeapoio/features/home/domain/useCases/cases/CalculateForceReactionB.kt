package com.example.reacaodeapoio.features.home.domain.useCases.cases

import javax.inject.Inject

class CalculateForceReactionB @Inject constructor() {
    operator fun invoke(
        forceValue: Float,
        forceReactionAValue: Float
    ) = forceValue - forceReactionAValue
}