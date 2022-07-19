package com.example.reacaodeapoio

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Classe responsável por conter o escopo da nossa aplicação (importante para a injeção de dependências)*/
@HiltAndroidApp
class SupportReactionApplication : Application()