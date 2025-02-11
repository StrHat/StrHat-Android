package com.konkuk.strhat.feature.selfdiagnosis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SelfDiagnosisRoute(
    padding: PaddingValues,
    viewModel: SelfDiagnosisViewModel = hiltViewModel()
) {
    SelfDiagnosisScreen(
        padding = padding
    )
}

@Composable
private fun SelfDiagnosisScreen(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "SelfDiagnosis Screen"
        )
    }
}