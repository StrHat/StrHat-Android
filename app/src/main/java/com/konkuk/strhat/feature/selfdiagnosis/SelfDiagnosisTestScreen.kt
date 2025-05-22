package com.konkuk.strhat.feature.selfdiagnosis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.util.KeyStorage.SELECTION_COUNT_4
import com.konkuk.strhat.core.util.KeyStorage.SELECTION_COUNT_5
import com.konkuk.strhat.core.util.KeyStorage.SELECTION_COUNT_NONE
import com.konkuk.strhat.domain.entity.SelfDiagnosisItem
import com.konkuk.strhat.domain.entity.SelfDiagnosisModel
import com.konkuk.strhat.domain.type.SelfDiagnosisTestType.PHQ9
import com.konkuk.strhat.domain.type.SelfDiagnosisTestType.PSS
import com.konkuk.strhat.domain.type.SelfDiagnosisTestType.SRI
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun SelfDiagnosisTestRoute(
    padding: PaddingValues,
    type: String,
    navigateToSelfDiagnosisResult: (String) -> Unit,
    viewModel: SelfDiagnosisViewModel = hiltViewModel()
) {
    val questions by viewModel.selfDiagnosisListModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getSelfDiagnosisQuestionList(type)
    }

    val selectedScores = remember { mutableStateMapOf<Int, Int>() }

    val testTotalScore = when (type) {
        PSS.testType -> selectedScores.values.sum() - 10
        SRI.testType -> selectedScores.values.sum()
        PHQ9.testType -> selectedScores.values.sum() - 9
        else -> 0
    }

    SelfDiagnosisTestScreen(
        padding = padding,
        type = type,
        navigateToSelfDiagnosisResult = {
            val selfDiagnosis = SelfDiagnosisModel(
                type = type,
                selfDiagnosisScore = testTotalScore
            )
            viewModel.postSelfDiagnosis(selfDiagnosis)

            navigateToSelfDiagnosisResult(type)
        },
        questions = questions,
        onSelectionChanged = { index, score ->
            selectedScores[index] = score
        }
    )
}

@Composable
fun SelfDiagnosisTestScreen(
    padding: PaddingValues,
    type: String,
    navigateToSelfDiagnosisResult: () -> Unit,
    questions: List<SelfDiagnosisItem>,
    onSelectionChanged: (index: Int, score: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val selections = remember { mutableStateMapOf<Int, Int>() }

    val selfDiagnosisTestScreenTitle = when (type) {
        PSS.testType -> stringResource(R.string.self_diagnosis_test_PSS_title)
        SRI.testType -> stringResource(R.string.self_diagnosis_test_SRI_title)
        PHQ9.testType -> stringResource(R.string.self_diagnosis_test_PHQ_9_title)
        else -> stringResource(R.string.self_diagnosis_test_default_title)
    }

    val selfDiagnosisTestScreenDescription = when (type) {
        PSS.testType -> stringResource(R.string.self_diagnosis_test_PSS_selection_description)
        SRI.testType -> stringResource(R.string.self_diagnosis_test_SRI_selection_description)
        PHQ9.testType -> stringResource(R.string.self_diagnosis_test_PHQ_9_selection_description)
        else -> stringResource(R.string.blank)
    }

    val selectionCount = when (type) {
        PSS.testType, SRI.testType -> SELECTION_COUNT_5
        PHQ9.testType -> SELECTION_COUNT_4
        else -> SELECTION_COUNT_NONE
    }

    Column(
        modifier = modifier.padding(16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = selfDiagnosisTestScreenTitle,
            style = typography.head1_b_24
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = selfDiagnosisTestScreenDescription,
            style = typography.body3_m_14,
            color = colors.Gray400
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(questions) { item ->
                Column(
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(
                        text = "${item.selfDiagnosisIndex}. ${item.selfDiagnosisQuestion}",
                        style = typography.body1_m_16,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        (1..selectionCount).forEach { score ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selections[item.selfDiagnosisIndex] == score,
                                    onClick = {
                                        selections[item.selfDiagnosisIndex] = score
                                        onSelectionChanged(item.selfDiagnosisIndex, score)
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = colors.MainBlue,
                                        unselectedColor = colors.Gray500
                                    )
                                )
                                Text(
                                    text =
                                        if (type == SRI.testType)
                                            score.toString()
                                        else
                                            (score - 1).toString()
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val allQuestionsCompleted = questions.all { selections.containsKey(it.selfDiagnosisIndex) }

        StrHatButton(
            isDisabled = !allQuestionsCompleted,
            text = stringResource(R.string.self_diagnosis_test_exit_button),
            onClick = {
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
            type = "pss",
            navigateToSelfDiagnosisResult = {},
            questions = emptyList(),
            onSelectionChanged = { _, _ -> }
        )
    }
}