package com.konkuk.strhat.feature.mypage

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.konkuk.strhat.core.component.SummaryBox
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.core.util.time.getWeekStateFromOffset
import com.konkuk.strhat.domain.entity.WeeklyStressScoreModel
import com.konkuk.strhat.feature.mypage.component.WeeklyBarChart
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MyStressEmotionChangeGraphRoute(
    padding: PaddingValues,
    date: String,
    navigateToMyPageStressScore: (String) -> Unit,
    navigateToMyPageAIFeedback: (String) -> Unit,
    viewModel: MyStressGraphViewModel = hiltViewModel()
) {
    var weekOffset by remember { mutableIntStateOf(0) }
    val initialDate = remember(date) { LocalDate.parse(date) }

    LaunchedEffect(weekOffset) {
        val targetDate = initialDate
            .minusWeeks(weekOffset.toLong())
            .format(DateTimeFormatter.ISO_LOCAL_DATE)
        viewModel.getWeeklyStressScore(targetDate)
    }

    val weeklyStressScoreModel by viewModel.weeklyStressScoreModel.collectAsState()

    val calculateBarDate: (Int) -> String = { index ->
        LocalDate
            .parse(weeklyStressScoreModel.startDate, DateTimeFormatter.ISO_LOCAL_DATE)
            .plusDays(index.toLong())
            .format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    val onStressBarClick = { index: Int ->
        navigateToMyPageStressScore(calculateBarDate(index))
    }
    val onEmotionBarClick = { index: Int ->
        navigateToMyPageAIFeedback(calculateBarDate(index))
    }

    MyStressEmotionChangeGraphScreen(
        padding = padding,
        date = date,
        weeklyStressScoreModel = weeklyStressScoreModel,
        weekOffset = weekOffset,
        onPrevWeekClick = { weekOffset += 1 },
        onNextWeekClick = { if (weekOffset > 0) weekOffset -= 1 },
        onStressBarClick = onStressBarClick,
        onEmotionBarClick = onEmotionBarClick,
        navigateToMyPageStressScore = navigateToMyPageStressScore,
        navigateToMyPageAIFeedback = navigateToMyPageAIFeedback
    )
}

@Composable
private fun MyStressEmotionChangeGraphScreen(
    padding: PaddingValues,
    date: String,
    weeklyStressScoreModel: WeeklyStressScoreModel,
    weekOffset: Int,
    onPrevWeekClick: () -> Unit,
    onNextWeekClick: () -> Unit,
    onStressBarClick: (Int) -> Unit,
    onEmotionBarClick: (Int) -> Unit,
    navigateToMyPageStressScore: (String) -> Unit,
    navigateToMyPageAIFeedback: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val weekState = getWeekStateFromOffset(weekOffset)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(top = 70.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = weeklyStressScoreModel.nickname,
                    style = typography.head1_b_24,
                    color = colors.MainBlue
                )
                Text(
                    text = stringResource(R.string.stress_score_nickname_description),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = stringResource(R.string.diary_calendar_month_left_arrow_description),
                    tint = colors.Gray300,
                    modifier = Modifier.noRippleClickable { onPrevWeekClick() }
                )
                Text(
                    text = "${weekState.month}",
                    style = typography.head0_b_26,
                    color = colors.MainBlue
                )
                Text(
                    text = stringResource(R.string.month),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${weekState.weekOfMonth}",
                    style = typography.head0_b_26,
                    color = colors.MainBlue
                )
                Text(
                    text = stringResource(R.string.week),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = stringResource(R.string.diary_calendar_month_right_arrow_description),
                    tint = colors.Gray300,
                    modifier = Modifier.noRippleClickable { onNextWeekClick() }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.my_graph_stress_change_weekly_summary),
            style = typography.head1_b_24,
            color = colors.MainBlack
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        SummaryBox(
            summary = weeklyStressScoreModel.weeklySummary,
            backgroundColor = colors.SubBlue
        )

        Spacer(modifier = Modifier.height(30.dp))

        PageDescriptionSection(
            titleResId = R.string.my_graph_stress_change_title,
            descriptionResId = R.string.my_graph_stress_change_description
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeeklyBarChart(
            values = weeklyStressScoreModel.stressLevels,
            onBarClick = onStressBarClick,
            modifier = Modifier.noRippleClickable {
                navigateToMyPageStressScore(date)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        PageDescriptionSection(
            titleResId = R.string.my_graph_emotion_change_title,
            descriptionResId = R.string.my_graph_emotion_change_description
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeeklyBarChart(
            values = weeklyStressScoreModel.emotionLevels,
            onBarClick = onEmotionBarClick,
            modifier = Modifier.noRippleClickable {
                navigateToMyPageAIFeedback(date)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyStressEmotionChangeGraphScreenPreview() {
    StrHatTheme {
        MyStressEmotionChangeGraphScreen(
            padding = PaddingValues(0.dp),
            date = "2025-05-01",
            weeklyStressScoreModel = WeeklyStressScoreModel(
                "",
                "",
                emptyList(),
                emptyList(),
                "",
                ""
            ),
            weekOffset = 1,
            onPrevWeekClick = {},
            onNextWeekClick = {},
            onStressBarClick = {},
            onEmotionBarClick = {},
            navigateToMyPageStressScore = {},
            navigateToMyPageAIFeedback = {}
        )
    }
}