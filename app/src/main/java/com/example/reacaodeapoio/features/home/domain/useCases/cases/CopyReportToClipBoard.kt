package com.example.reacaodeapoio.features.home.domain.useCases.cases

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.features.home.domain.HomeRepository
import com.example.reacaodeapoio.features.home.domain.model.ResultModel
import javax.inject.Inject

/**
 * Lógica responsável por copiar o relatório
 * @param context contexto da aplicação
 * @param homeRepository repositório que informa o relatório */
class CopyReportToClipBoard @Inject constructor(
    private val context: Context,
    private val homeRepository: HomeRepository,
) {

    /**
     * @param resultModel modelo que contém o resultado calculado e os dados informados para tal */
    operator fun invoke(resultModel: ResultModel) {
        val textOutput = homeRepository.getCopyOutputText(resultModel) // Pega o texto do relatório informando o resultado encontrado
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager // Acessa o teclado do usuário
        val clipData = ClipData.newPlainText(context.getString(R.string.app_name), textOutput) // Acessa o conteúdo do teclado do usuário
        clipboard.setPrimaryClip(clipData) // Coloca no "paste" o conteúdo do relatório
    }
}