package com.konkuk.strhat.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.konkuk.strhat.core.util.KeyStorage.CALENDAR_COLUMN_COUNT
import com.konkuk.strhat.core.util.KeyStorage.CALENDAR_TOTAL_CELLS
import com.konkuk.strhat.core.util.KeyStorage.FIRST_DAY_MAX_INDEX
import com.konkuk.strhat.core.util.KeyStorage.FIRST_DAY_OF_MONTH
import com.konkuk.strhat.core.util.KeyStorage.MIN_OFFSET
import com.konkuk.strhat.core.util.KeyStorage.PLUS_MINUS_OF_MONTH
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.feature.diary.component.CalendarDateCell
import com.konkuk.strhat.feature.diary.component.CalendarDayOfWeekCell
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
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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

            val firstDayOfMonth = currentMonth.atDay(FIRST_DAY_OF_MONTH)
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
                        .noRippleClickable { currentMonth = currentMonth.minusMonths(PLUS_MINUS_OF_MONTH) }
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
                        .noRippleClickable { currentMonth = currentMonth.plusMonths(PLUS_MINUS_OF_MONTH) }
                )
            }

            CalendarDayOfWeekCell()

            val totalCells = CALENDAR_TOTAL_CELLS
            val daysOfMonthList = mutableListOf<LocalDate?>()

            val offset = if (firstDayIndex == FIRST_DAY_MAX_INDEX) MIN_OFFSET else firstDayIndex
            repeat(offset) {
                daysOfMonthList.add(null)
            }

            for (day in FIRST_DAY_OF_MONTH..daysInMonth) {
                daysOfMonthList.add(LocalDate(currentMonth.year, currentMonth.monthValue, day))
            }

            while (daysOfMonthList.size < totalCells) {
                daysOfMonthList.add(null)
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(CALENDAR_COLUMN_COUNT),
                modifier = Modifier.fillMaxSize()
            ) {
                items(daysOfMonthList.size) { index ->
                    val date = daysOfMonthList[index]
                    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

                    CalendarDateCell(
                        date = date,
                        today = today
                    )
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