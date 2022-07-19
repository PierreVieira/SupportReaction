package com.example.reacaodeapoio.features.home.domain.useCases.cases

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.features.home.domain.HomeRepository
import com.example.reacaodeapoio.features.home.domain.model.ResultModel
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CopyReportToClipBoard @Inject constructor(
    private val context: Context,
    private val homeRepository: HomeRepository,
) {

    operator fun invoke(resultModel: ResultModel) {
        val textOutput = homeRepository.getFileOutputText(resultModel) // Pega o texto do relatório informando o resultado encontrado
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager // Acessa o teclado do usuário
        val clipData = ClipData.newPlainText(context.getString(R.string.app_name), textOutput) // Acessa o conteúdo do teclado do usuário
        clipboard.setPrimaryClip(clipData) // Coloca no "paste" o conteúdo do relatório
    }
}