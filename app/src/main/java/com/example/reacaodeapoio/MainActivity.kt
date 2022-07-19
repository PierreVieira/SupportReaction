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
        setContent { // Inicializa o conteúdo
            SupportReactionTheme { // Inicializa o tema do app
                DestinationsNavHost(navGraph = NavGraphs.root) // Configura a navegação entre as telas
            }
        }
    }
}