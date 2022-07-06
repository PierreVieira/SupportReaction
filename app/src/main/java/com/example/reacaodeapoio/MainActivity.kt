package com.example.reacaodeapoio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.reacaodeapoio.features.NavGraphs
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // Extensão da activity que faz com que ela chame funções composable
            SupportReactionTheme { // Chamada do tema do app (cores, fontes e espaçamentos)
                DestinationsNavHost(navGraph = NavGraphs.root) // Configuração da navegação entre telas do app
            }
        }
    }
}