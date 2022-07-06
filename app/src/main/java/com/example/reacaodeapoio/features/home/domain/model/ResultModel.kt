package com.example.reacaodeapoio.features.home.domain.model

/**
 * Modelo que contém o resultado calculado e os dados informados para tal
 * @param forceText texto que contém a força aplicada
 * @param firstDistanceText texto que contém a primeira distância
 * @param secondDistanceText texto que contém a segunda distância
 * @param reactionA texto que contém o valor da reação em A
 * @param reactionB texto que contém o valor da reação em B */
data class ResultModel(
    val forceText: String,
    val firstDistanceText: String,
    val secondDistanceText: String,
    val reactionA: String,
    val reactionB: String
)