package com.konkuk.strhat.feature.mypage

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
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.domain.type.SelfDiagnosisTestType
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun MySelfDiagnosisRecordRoute(
    padding: PaddingValues,
    navigateToMySelfDiagnosisRecordResult: (String) -> Unit,
) {
    MySelfDiagnosisRecordScreen(
        padding = padding,
        onResultRecordBtnClick = navigateToMySelfDiagnosisRecordResult
    )
}

@Composable
fun MySelfDiagnosisRecordScreen(
    padding: PaddingValues,
    onResultRecordBtnClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.my_self_diagnosis_record_screen_title),
            style = typography.head1_b_24,
            color = colors.MainBlack
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.my_self_diagnosis_record_stress_title),
            style = typography.head2_b_20,
            color = colors.MainBlack
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.my_self_diagnosis_record_description_PSS),
            style = typography.body2_r_15,
            color = colors.Gray500
        )

        Spacer(modifier = Modifier.height(18.dp))

        StrHatButton(
            text = stringResource(R.string.my_self_diagnosis_record_PSS_button),
            onClick = { onResultRecordBtnClick(SelfDiagnosisTestType.PSS.testType) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.my_self_diagnosis_record_description_SRI),
            style = typography.body2_r_15,
            color = colors.Gray500
        )

        Spacer(modifier = Modifier.height(18.dp))

        StrHatButton(
            text = stringResource(R.string.my_self_diagnosis_record_SRI_button),
            onClick = { onResultRecordBtnClick(SelfDiagnosisTestType.SRI.testType) }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.my_self_diagnosis_record_depression_title),
            style = typography.head2_b_20,
            color = colors.MainBlack
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.my_self_diagnosis_record_description_PHQ_9),
            style = typography.body2_r_15,
            color = colors.Gray500
        )

        Spacer(modifier = Modifier.height(18.dp))

        StrHatButton(
            text = stringResource(R.string.my_self_diagnosis_record_PHQ_9_button),
            onClick = { onResultRecordBtnClick(SelfDiagnosisTestType.PHQ9.testType) }
        )
    }
}

@Preview
@Composable
fun MySelfDiagnosisRecordScreenPreview() {
    StrHatTheme {
        MySelfDiagnosisRecordScreen(
            padding = PaddingValues(),
            onResultRecordBtnClick = {}
        )
    }
}