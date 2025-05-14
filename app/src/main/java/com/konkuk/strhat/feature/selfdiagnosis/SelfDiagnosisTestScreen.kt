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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.domain.entity.SelfDiagnosisItem
import com.konkuk.strhat.domain.entity.SelfDiagnosisModel
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun SelfDiagnosisTestRoute(
    padding: PaddingValues,
    type: String,
    navigateToSelfDiagnosisResult: () -> Unit,
    viewModel: SelfDiagnosisViewModel = hiltViewModel()
) {
    val questions by viewModel.selfDiagnosisListModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getSelfDiagnosisQuestionList(type)
    }

    val selectedScores = remember { mutableStateMapOf<Int, Int>() }

    SelfDiagnosisTestScreen(
        padding = padding,
        type = type,
        navigateToSelfDiagnosisResult = {
            val totalScore = selectedScores.values.sum()
            val selfDiagnosis = SelfDiagnosisModel(
                type = type,
                selfDiagnosisScore = totalScore
            )
            viewModel.postSelfDiagnosis(selfDiagnosis)

            navigateToSelfDiagnosisResult()
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
        "pss" -> "스틀햇과 함께 하는 PSS 검사"
        "sri" -> "스틀햇과 함께 하는 SRI 검사"
        "phq9" -> "스틀햇과 함께 하는 PHQ-9 검사"
        else -> "스틀햇과 함께 하는 자가진단 검사"
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
            text = "1 : 전혀 그렇지 않다, 2 : 그렇지 않다,\n\n3 : 보통이다, 4 : 그렇다, 5 : 매우 그렇다",
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
                        (1..5).forEach { score ->
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
                                Text(text = score.toString())
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        StrHatButton(
            text = "검사 종료하기",
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