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
        setContent {
            SupportReactionTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}