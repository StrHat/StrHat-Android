package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.bottomsheet.DatePickerBottomSheet
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.core.util.time.currentDate
import com.konkuk.strhat.feature.mypage.state.MySelfDiagnosisRecordResultState
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun MySelfDiagnosisRecordResultRoute(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
    viewModel: MySelfDiagnosisRecordViewModel = hiltViewModel()
) {
    val mySelfDiagnosisRecordResultState by viewModel.mySelfDiagnosisRecordResultState.collectAsState()

    MySelfDiagnosisRecordResultScreen(
        padding = padding,
        mySelfDiagnosisRecordResultState = mySelfDiagnosisRecordResultState,
        navigateToMyPage = navigateToMyPage
    )
}

@Composable
fun MySelfDiagnosisRecordResultScreen(
    padding: PaddingValues,
    mySelfDiagnosisRecordResultState: MySelfDiagnosisRecordResultState,
    navigateToMyPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isDatePickerBottomSheetVisible by remember { mutableStateOf(false) }

    val initialDate = mySelfDiagnosisRecordResultState.selectedDate ?: currentDate
    var selectedLocalDate by remember { mutableStateOf(initialDate) }

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = mySelfDiagnosisRecordResultState.nickname,
                        style = typography.head1_b_24,
                        color = colors.MainBlue
                    )
                    Text(
                        text = stringResource(R.string.self_diagnosis_result_nickname_description),
                        style = typography.head1_b_24,
                        color = colors.MainBlack
                    )
                }

                Row(
                    modifier = Modifier
                        .noRippleClickable {
                            isDatePickerBottomSheetVisible = true
                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = stringResource(R.string.my_self_diagnosis_record_result_calendar_icon_description),
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = selectedLocalDate.toString().substring(5, 7),
                        style = typography.head0_b_26,
                        color = colors.MainBlue,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Text(
                        text = stringResource(R.string.my_self_diagnosis_record_result_date_month),
                        style = typography.head1_b_24,
                        color = colors.MainBlack
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = selectedLocalDate.toString().substring(8, 10),
                        style = typography.head0_b_26,
                        color = colors.MainBlue,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Text(
                        text = stringResource(R.string.my_self_diagnosis_record_result_date_day),
                        style = typography.head1_b_24,
                        color = colors.MainBlack
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = mySelfDiagnosisRecordResultState.testType,
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
                    text = mySelfDiagnosisRecordResultState.stressScore.toString(),
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
                    text = mySelfDiagnosisRecordResultState.stressLevel,
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
                text = mySelfDiagnosisRecordResultState.stressLevelDescription,
                style = typography.body2_r_15,
                color = colors.MainBlack
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = mySelfDiagnosisRecordResultState.testTypeDescription,
                style = typography.body2_r_15,
                color = colors.MainBlack
            )
        }

        StrHatButton(
            text = stringResource(R.string.confirm),
            onClick = {
                navigateToMyPage()
            },
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }

    if (isDatePickerBottomSheetVisible) {
        DatePickerBottomSheet(
            isVisible = isDatePickerBottomSheetVisible,
            selectedDateString = mySelfDiagnosisRecordResultState.selectedDateString,
            selectedDate = mySelfDiagnosisRecordResultState.selectedDate,
            onDismiss = { isDatePickerBottomSheetVisible = false },
            onDateSelected = { selectedDate ->
                isDatePickerBottomSheetVisible = false
            }
        )
    }
}

@Preview
@Composable
fun MySelfDiagnosisResultScreenPreview() {
    StrHatTheme {
        val mySelfDiagnosisRecordResultExampleState = MySelfDiagnosisRecordResultState(
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

        MySelfDiagnosisRecordResultScreen(
            padding = PaddingValues(),
            mySelfDiagnosisRecordResultState = mySelfDiagnosisRecordResultExampleState,
            navigateToMyPage = {}
        )
    }
}