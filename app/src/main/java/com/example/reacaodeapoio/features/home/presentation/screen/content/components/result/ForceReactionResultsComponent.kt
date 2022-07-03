package com.example.reacaodeapoio.features.home.presentation.screen.content.components.result

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reacaodeapoio.R
import com.example.reacaodeapoio.ui.components.spacers.VerticalSpacer
import com.example.reacaodeapoio.ui.theme.SupportReactionTheme

@Composable
fun ForceReactionResultsComponent(
    reactionA: String,
    reactionB: String,
    onCopyReportClick: () -> Unit,
    onClearResultsClick: () -> Unit,
) {
    HomeResultTextsComponent(
        reactionA = reactionA,
        reactionB = reactionB
    )
    VerticalSpacer()
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onCopyReportClick
    ) {
        Text(text = stringResource(id = R.string.download_report_result))
    }
    VerticalSpacer(12.dp)
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClearResultsClick,
        colors = ButtonDefaults.buttonColors()
    ) {
        Text(text = stringResource(id = R.string.clear_results_text_button))
    }
}

@Composable
@Preview(showBackground = true)
fun ForceReactionResultsComponentPreview() = SupportReactionTheme {
    Column {
        ForceReactionResultsComponent(
            reactionA = "2.146 Newtons",
            reactionB = "1.1 Newtons",
            onCopyReportClick = {}
        ) {}
    }
}