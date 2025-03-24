package com.konkuk.strhat.feature.selfdiagnosis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

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
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.self_diagnosis_screen_stress_title),
            style = typography.head1_b_24,
            color = colors.MainBlack
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.self_diagnosis_description_PSS),
            style = typography.body2_r_15,
            color = colors.Gray500
        )

        Spacer(modifier = Modifier.height(18.dp))

        StrHatButton(
            text = stringResource(R.string.self_diagnosis_PSS_button),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.self_diagnosis_description_SRI),
            style = typography.body2_r_15,
            color = colors.Gray500
        )

        Spacer(modifier = Modifier.height(18.dp))

        StrHatButton(
            text = stringResource(R.string.self_diagnosis_SRI_button),
            onClick = {}
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = stringResource(R.string.self_diagnosis_screen_depression_title),
            style = typography.head1_b_24,
            color = colors.MainBlack
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.self_diagnosis_description_PHQ_9),
            style = typography.body2_r_15,
            color = colors.Gray500
        )

        Spacer(modifier = Modifier.height(18.dp))

        StrHatButton(
            text = stringResource(R.string.self_diagnosis_PHQ_9_button),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun SelfDiagnosisScreenPreview() {
    StrHatTheme {
        SelfDiagnosisScreen(
            padding = PaddingValues()
        )
    }
}