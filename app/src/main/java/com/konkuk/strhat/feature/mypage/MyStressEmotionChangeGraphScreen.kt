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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.SummaryBox
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.feature.mypage.component.WeeklyBarChart
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun MyStressEmotionChangeGraphRoute(
    padding: PaddingValues
) {
    MyStressEmotionChangeGraphScreen(
        padding = padding
    )
}

@Composable
private fun MyStressEmotionChangeGraphScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
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
                    text = "송밍서",
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
                    tint = colors.Gray300
                )
                Text(
                    text = "12",
                    style = typography.head0_b_26,
                    color = colors.MainBlue
                )
                Text(
                    text = "월",
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "1",
                    style = typography.head0_b_26,
                    color = colors.MainBlue
                )
                Text(
                    text = "주차",
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = stringResource(R.string.diary_calendar_month_right_arrow_description),
                    tint = colors.Gray300
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "주차별 스트레스 요약",
            style = typography.head1_b_24,
            color = colors.MainBlack
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        SummaryBox(
            summary = "사용자는 완벽주의적이고 내성적인 성향을 가지고 있어서 발표나 의견 충돌, 야근, 인적 손실, 신체적 피로, 그리고 가족 관련 걱정 등 다양한 요인들이 스트레스를 유발했을 것으로 판단됩니다. 이러한 스트레스 요인들이 하나씩 쌓이며 사용자의 마음과 몸에 부담을 주었을 것입니다. 스트레스 관리를 위해 업무에서 완벽을 추구하는 것보다 실수를 수용하고 동료와 의견을 잘 조율하며, 일과 휴식을 균형 있게 유지하는 것이 중요해 보입니다.",
            backgroundColor = colors.SubBlue
        )

        Spacer(modifier = Modifier.height(30.dp))

        PageDescriptionSection(
            titleResId = R.string.my_graph_stress_change_title,
            descriptionResId = R.string.my_graph_stress_change_description
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeeklyBarChart(
            values = listOf(10, 2, 3, 4, 6, 7, 9)
        )

        Spacer(modifier = Modifier.height(30.dp))

        PageDescriptionSection(
            titleResId = R.string.my_graph_emotion_change_title,
            descriptionResId = R.string.my_graph_emotion_change_description
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeeklyBarChart(
            values = listOf(10, 2, 3, 4, 6, 7, 9)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyStressEmotionChangeGraphScreenPreview() {
    StrHatTheme {
        MyStressEmotionChangeGraphScreen(
            padding = PaddingValues(0.dp)
        )
    }
}