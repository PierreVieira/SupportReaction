package com.example.reacaodeapoio.features.home.domain.model

import androidx.annotation.StringRes

data class HomeDropDownMenuItemModel(
    @StringRes val name: Int,
    val action: () -> Unit
)