package com.konkuk.strhat.feature.selfdiagnosis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun SelfDiagnosisTestRoute(
    padding: PaddingValues,
    navigateToSelfDiagnosisResult: () -> Unit
) {
    SelfDiagnosisTestScreen(
        padding = padding,
        navigateToSelfDiagnosisResult = navigateToSelfDiagnosisResult
    )
}

@Composable
fun SelfDiagnosisTestScreen(
    padding: PaddingValues,
    navigateToSelfDiagnosisResult: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.self_diagnosis_test_screen_title),
            modifier = Modifier.noRippleClickable {
                navigateToSelfDiagnosisResult()
            }
        )
    }
}

@Preview
@Composable
fun SelfDiagnosisTestScreenPreview() {
    StrHatTheme {
        SelfDiagnosisTestScreen(
            padding = PaddingValues(),
            navigateToSelfDiagnosisResult = {}
        )
    }
}