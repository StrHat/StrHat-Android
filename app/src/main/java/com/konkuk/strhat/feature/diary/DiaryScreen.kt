package com.konkuk.strhat.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.time.YearMonth

@Composable
fun DiaryRoute(
    padding: PaddingValues,
    viewModel: DiaryViewModel = hiltViewModel()
) {
    DiaryScreen(
        padding = padding
    )
}

@Composable
private fun DiaryScreen(
    padding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(colors.MainWhite)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.diary_screen_title),
                style = typography.head1_b_24,
                color = colors.MainBlack
            )

            var currentMonth by remember { mutableStateOf(YearMonth.now()) }

            val firstDayOfMonth = currentMonth.atDay(1)
            val daysInMonth = currentMonth.lengthOfMonth()
            val firstDayIndex = firstDayOfMonth.dayOfWeek.value

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = stringResource(R.string.diary_calendar_month_left_arrow_description),
                    tint = colors.Gray300,
                    modifier = Modifier
                        .noRippleClickable { currentMonth = currentMonth.minusMonths(1) }
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "${currentMonth.year}년 ${currentMonth.monthValue}월",
                    style = typography.title1_b_18,
                    color = colors.Gray900
                )

                Spacer(modifier = Modifier.width(20.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    contentDescription = stringResource(R.string.diary_calendar_month_right_arrow_description),
                    tint = colors.Gray300,
                    modifier = Modifier
                        .noRippleClickable { currentMonth = currentMonth.plusMonths(1) }
                )
            }

            val dayOfWeekList = listOf("일", "월", "화", "수", "목", "금", "토")

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                dayOfWeekList.forEach { label ->
                    Text(
                        text = label,
                        style = typography.body1_b_16,
                        color = colors.Gray500,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentSize()
                            .padding(vertical = 4.dp)
                    )
                }
            }

            val totalCells = 42
            val daysOfMonthList = mutableListOf<LocalDate?>()

            val offset = if (firstDayIndex == 7) 0 else firstDayIndex
            repeat(offset) {
                daysOfMonthList.add(null)
            }

            for (day in 1..daysInMonth) {
                daysOfMonthList.add(LocalDate(currentMonth.year, currentMonth.monthValue, day))
            }

            while (daysOfMonthList.size < totalCells) {
                daysOfMonthList.add(null)
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.fillMaxSize()
            ) {
                items(daysOfMonthList.size) { index ->
                    val date = daysOfMonthList[index]
                    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (date != null) {
                            val textColor = when {
                                date == today -> colors.MainBlue
                                date < today  -> colors.MainBlack
                                else          -> colors.Gray300
                            }

                            Text(
                                text = date.dayOfMonth.toString(),
                                style = typography.body1_m_16,
                                color = textColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun DiaryScreenPreview() {
    DiaryScreen(padding = PaddingValues(0.dp))
}