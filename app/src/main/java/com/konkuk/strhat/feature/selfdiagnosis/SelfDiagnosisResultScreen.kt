package com.konkuk.strhat.feature.selfdiagnosis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.feature.selfdiagnosis.state.SelfDiagnosisResultState
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun SelfDiagnosisResultRoute(
    padding: PaddingValues,
    navigateToSelfDiagnosis: () -> Unit,
    viewModel: SelfDiagnosisViewModel = hiltViewModel()
) {
    val selfDiagnosisResultState by viewModel.selfDiagnosisResultState.collectAsState()

    SelfDiagnosisResultScreen(
        padding = padding,
        selfDiagnosisResultState = selfDiagnosisResultState,
        navigateToSelfDiagnosis = navigateToSelfDiagnosis
    )
}

@Composable
fun SelfDiagnosisResultScreen(
    padding: PaddingValues,
    selfDiagnosisResultState: SelfDiagnosisResultState,
    navigateToSelfDiagnosis: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row {
                Text(
                    text = selfDiagnosisResultState.nickname,
                    style = typography.head1_b_24,
                    color = colors.MainBlue
                )
                Text(
                    text = stringResource(R.string.self_diagnosis_result_nickname_description),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            
            Row {
                Text(
                    text = selfDiagnosisResultState.testType,
                    style = typography.head0_b_26,
                    color = colors.MainBlack,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(R.string.self_diagnosis_result_test_type_description),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
            }

            Text(
                text = stringResource(R.string.self_diagnosis_result_stress_score_description),
                style = typography.head1_b_24,
                color = colors.MainBlack
            )

            Row {
                Text(
                    text = selfDiagnosisResultState.stressScore.toString(),
                    style = typography.head0_b_26,
                    color = colors.MainBlue,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(R.string.self_diagnosis_result_stress_score_end),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row {
                Text(
                    text = selfDiagnosisResultState.stressLevel,
                    style = typography.head2_b_20,
                    color = colors.MainBlue
                )
                Text(
                    text = stringResource(R.string.self_diagnosis_result_stress_level_end),
                    style = typography.head2_r_20,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = selfDiagnosisResultState.stressLevelDescription,
                style = typography.body2_r_15,
                color = colors.MainBlack
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = selfDiagnosisResultState.testTypeDescription,
                style = typography.body2_r_15,
                color = colors.MainBlack
            )
        }

        StrHatButton(
            text = stringResource(R.string.self_diagnosis_result_to_self_diagnosis_main),
            onClick = navigateToSelfDiagnosis,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Preview
@Composable
fun SelfDiagnosisResultScreenPreview() {
    StrHatTheme {
        val selfDiagnosisResultExampleState = SelfDiagnosisResultState(
            nickname = "송민서",
            testType = "PSS",
            stressScore = 10,
            stressLevel = "정상 스트레스 수준",
            stressLevelDescription = "느끼고 있는 스트레스 정도는 정상적인 수준으로,\n심리적으로 안정되어 있습니다.",
            testTypeDescription = "0~13점: 정상 스트레스 수준\n" +
                    "14~16점: 경미한 스트레스 수준\n" +
                    "17~18점: 중간 스트레스 수준\n" +
                    "19점: 높은 스트레스 수준\n\n" + "이 척도는 Cohen, Kamarck과 Mermelstein (1983)의 지각된 스트레스 척도를 한국 실정에 맞게 번안하여 한국 대학생을 대상으로 타당화한 것입니다.\n" +
                    "한국심리학회 홈페이지 또는 KSI한국학술정보 홈페이지에서 원문을 보실 수 있습니다."
        )

        SelfDiagnosisResultScreen(
            padding = PaddingValues(),
            selfDiagnosisResultState = selfDiagnosisResultExampleState,
            navigateToSelfDiagnosis = {}
        )
    }
}