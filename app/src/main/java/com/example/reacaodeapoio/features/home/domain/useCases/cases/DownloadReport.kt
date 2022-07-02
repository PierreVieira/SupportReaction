package com.example.reacaodeapoio.features.home.domain.useCases.cases

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import com.example.reacaodeapoio.features.home.domain.HomeRepository
import com.example.reacaodeapoio.features.home.domain.model.ResultModel
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DownloadReport @Inject constructor(
    private val context: Context,
    private val formatter: DateTimeFormatter,
    private val homeRepository: HomeRepository,
) {

    operator fun invoke(resultModel: ResultModel) {
        val current = LocalDateTime.now()
        val formatted = current.format(formatter)
        val textOutput = homeRepository.getFileOutputText(resultModel)
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("reação de apoio", textOutput)
        clipboard.setPrimaryClip(clipData)
    }
}