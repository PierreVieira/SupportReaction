package com.example.reacaodeapoio.features.home.domain

import com.example.reacaodeapoio.features.home.domain.model.ResultModel

/**
 * Interface que fornece os dados do relatório copiado */
interface HomeRepository {
    fun getCopyOutputText(resultModel: ResultModel): String
}