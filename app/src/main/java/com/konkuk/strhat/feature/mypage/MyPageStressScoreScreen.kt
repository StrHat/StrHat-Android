package com.konkuk.strhat.feature.mypage

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.SummaryBox
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.domain.entity.StressScoreModel
import com.konkuk.strhat.feature.diary.StressScoreViewModel
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MyPageStressScoreRoute(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: StressScoreViewModel = hiltViewModel()
) {
    val stressScoreState by viewModel.stressScoreState.collectAsState()

    val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

    LaunchedEffect(Unit) {
        viewModel.getStressScore(today)
    }

    MyPageStressScoreScreen(
        padding = padding,
        stressScoreState = stressScoreState,
        popBackStack = popBackStack
    )
}

@Composable
fun MyPageStressScoreScreen(
    padding: PaddingValues,
    stressScoreState: StressScoreModel,
    popBackStack: () -> Unit,
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
                    text = stressScoreState.nickname,
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

            Row {
                Text(
                    text = stringResource(R.string.my_page_stress_score_title),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stressScoreState.stressScore.toString(),
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

            Spacer(modifier = Modifier.height(40.dp))

            Row {
                Text(
                    text = stressScoreState.level,
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

            SummaryBox(
                summary = stressScoreState.analysis,
                backgroundColor = colors.Gray100
            )
        }

        StrHatButton(
            text = stringResource(R.string.my_page_go_back_button),
            onClick = {
                popBackStack()
            },
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Preview
@Composable
fun MyPageStressScoreScreenPreview() {
    StrHatTheme {
        MyPageStressScoreScreen(
            padding = PaddingValues(),
            stressScoreState = StressScoreModel("", 1, "", "", ""),
            popBackStack = {}
        )
    }
}