package com.example.reacaodeapoio.features.home.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.ui.components.GrayDivider

@Composable
fun HomeOptionsDropdownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onDownloadsClick: () -> Unit,
    onMoreInfoClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopEnd)
) {
    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.downloads))
            },
            onClick = onDownloadsClick
        )
        GrayDivider(modifier = Modifier.padding(horizontal = 12.dp))
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.infos))
            },
            onClick = onMoreInfoClick
        )
    }
}