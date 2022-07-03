package com.example.reacaodeapoio.features.home.domain.useCases

import com.example.reacaodeapoio.features.home.domain.useCases.cases.*

data class HomeUseCases(
    val calculateForceReactionA: CalculateForceReactionA,
    val calculateForceReactionB: CalculateForceReactionB,
    val isValidText: IsValidFloatText,
    val resultFormatter: ResultFormatter,
    val copyReportToClipBoard: CopyReportToClipBoard,
    val replaceCommaForDot: ReplaceCommaForDot
)