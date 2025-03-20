package com.konkuk.strhat.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.feature.diary.component.DiaryAIFeedbackSummaryBox
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun StressScoreRoute(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    viewModel: StressScoreViewModel = hiltViewModel()
) {
    val nickname by viewModel.nickname.collectAsState()
    val stressScore by viewModel.stressScore.collectAsState()
    val level by viewModel.level.collectAsState()
    val analysis by viewModel.analysis.collectAsState()

    StressScoreScreen(
        padding = padding,
        nickname = nickname,
        stressScore = stressScore,
        level = level,
        analysis = analysis,
        navigateToHome = navigateToHome
    )
}

@Composable
fun StressScoreScreen(
    padding: PaddingValues,
    nickname: String,
    stressScore: Int,
    level: String,
    analysis: String,
    navigateToHome: () -> Unit,
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
                    text = nickname,
                    style = typography.head1_b_24,
                    color = colors.MainBlue
                )
                Text(
                    text = stringResource(R.string.stress_score_nickname_description),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.stress_score_today_title),
                style = typography.head1_b_24,
                color = colors.MainBlack
            )

            Row {
                Text(
                    text = stressScore.toString(),
                    style = typography.head0_b_26,
                    color = colors.Gray400,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(R.string.stress_score_stress_score_end),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = level,
                    style = typography.head2_b_20,
                    color = colors.Gray400,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = stringResource(R.string.stress_score_level_end),
                    style = typography.head2_r_20,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.stress_score_level_description),
                style = typography.body2_r_15,
                color = colors.MainBlack
            )

            Spacer(modifier = Modifier.height(46.dp))

            PageDescriptionSection(
                titleResId = R.string.stress_score_analysis_title
            )

            Spacer(modifier = Modifier.height(16.dp))

            DiaryAIFeedbackSummaryBox(
                diaryAIFeedbackSummary = analysis
            )
        }

        StrHatButton(
            text = stringResource(R.string.stress_score_to_home),
            onClick = {
                navigateToHome()
            },
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Preview
@Composable
fun StressScoreScreenPreview() {
    StrHatTheme {
        StressScoreScreen(
            padding = PaddingValues(),
            nickname = "송민서",
            stressScore = 6,
            level = "보통 스트레스 수준",
            analysis = "사용자는 다양한 취향을 가진 다양한 활동을 즐기며 삶을 즐기는 편인데, 시험 기간에는 공부 부담과 시간 부족으로 인한 스트레스를 많이 받는 것으로 보입니다. 여러 전공 과목을 동시에 공부해야 하는 상황에서 과연 배워야 할 것들이 끝이 없다는 생각이 불안을 유발하며, 이로 인해 조급함과 지쳐감을 느끼고 있는 모습입니다. 이외에도 자신이 즐기는 음악 청취나 외향적인 성향의 활동을 쉽게 할 수 없다는 점이 스트레스를 느끼는데 영향을 줄 수 있습니다.",
            navigateToHome = {}
        )
    }
}