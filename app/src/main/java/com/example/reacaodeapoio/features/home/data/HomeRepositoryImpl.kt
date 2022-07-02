package com.example.reacaodeapoio.features.home.data

import com.example.reacaodeapoio.features.home.domain.HomeRepository
import com.example.reacaodeapoio.features.home.domain.model.ResultModel
import com.example.reacaodeapoio.features.home.domain.useCases.cases.ResultFormatter
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val resultFormatter: ResultFormatter,
) : HomeRepository {
    override fun getFileOutputText(
        resultModel: ResultModel,
    ): String = with(resultModel) {
        val totalDistance =
            resultFormatter(firstDistanceText.toFloat() + secondDistanceText.toFloat())
        "O problema proposto avalia e calcula as reações de apoio em uma viga biapoiada de comprimento $totalDistance, com uma carga concentrada de $forceText N posicionada a uma distância $firstDistanceText, tendo como referência o apoio A.\n" +
                "Para solucionar o problema aplica-se as equações de equilíbrio estático para calcular as reações nos apoios A e B.\n" +
                "Primeiramente, aplicou-se a somatória dos momentos no apoio A e igualou-se a zero, adotando o sentido horário positivo. Assim é possível encontrar a reação de apoio no ponto B, \n" +
                "∑▒〖M_A=0〗\n" +
                "F x L1 – RB x (L1+L2) = 0\n" +
                "$forceText * $firstDistanceText – RB * $totalDistance =0\n" +
                "RB=($forceText x $firstDistanceText)/($firstDistanceText + $secondDistanceText)\n" +
                "RB= $reactionB\n" +
                "\n" +
                "Portanto, o valor da reação de apoio em B é de $reactionB N.\n" +
                "\n" +
                "No segundo momento, com uma das variáveis já conhecidas, aplica-se a somatória das forças verticais e iguala-se a zero, adotando sentido positivo para cima. Dessa forma, \n" +
                "∑▒〖F_Y=0〗\n" +
                "RA + RB – F = 0 \n" +
                "RA + $reactionB = $forceText\n" +
                "RA = $forceText - $reactionB\n" +
                "RA = $reactionA\n" +
                "\n" +
                "Portanto, o valor da reação de apoio em A é de $reactionA.\n"
    }
}