package com.example.reacaodeapoio.features.home.domain

import com.example.reacaodeapoio.features.home.domain.model.ResultModel

interface HomeRepository {
    fun getFileOutputText(resultModel: ResultModel): String
}