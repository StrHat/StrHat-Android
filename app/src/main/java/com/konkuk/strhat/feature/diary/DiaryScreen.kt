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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.util.KeyStorage.CALENDAR_COLUMN_COUNT
import com.konkuk.strhat.core.util.KeyStorage.CALENDAR_TOTAL_CELLS
import com.konkuk.strhat.core.util.KeyStorage.FIRST_DAY_MAX_INDEX
import com.konkuk.strhat.core.util.KeyStorage.FIRST_DAY_OF_MONTH
import com.konkuk.strhat.core.util.KeyStorage.MIN_OFFSET
import com.konkuk.strhat.core.util.KeyStorage.PLUS_MINUS_OF_MONTH
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.domain.entity.DiaryExistenceModel
import com.konkuk.strhat.feature.diary.component.AddDiaryFloatingButton
import com.konkuk.strhat.feature.diary.component.CalendarDateCell
import com.konkuk.strhat.feature.diary.component.CalendarDayOfWeekCell
import com.konkuk.strhat.feature.diary.component.DiaryDateUnselectedEmptyView
import com.konkuk.strhat.feature.diary.component.DiarySummaryView
import com.konkuk.strhat.feature.diary.component.NoDiaryEmptyView
import com.konkuk.strhat.feature.diary.state.Diary
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
    navigateToAddDiary: () -> Unit,
    navigateToDiaryAIFeedback: (String) -> Unit,
    viewModel: DiaryViewModel = hiltViewModel()
) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val selectedDiary by viewModel.selectedDiary.collectAsState()
    val diaryExistenceState by viewModel.diaryExistenceState.collectAsState()

    DiaryScreen(
        padding = padding,
        selectedDate = selectedDate,
        selectedDiary = selectedDiary,
        onDateSelected = viewModel::onDateSelected,
        diaryExistenceState = diaryExistenceState,
        onFloatingBtnClick = navigateToAddDiary,
        onSummaryViewClick = navigateToDiaryAIFeedback
    )
}

@Composable
private fun DiaryScreen(
    padding: PaddingValues,
    selectedDate: LocalDate?,
    selectedDiary: Diary?,
    onDateSelected: (LocalDate) -> Unit,
    diaryExistenceState: DiaryExistenceModel,
    onFloatingBtnClick: () -> Unit,
    onSummaryViewClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.MainWhite)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            PageDescriptionSection(
                titleResId = R.string.diary_screen_title
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
                modifier = Modifier.fillMaxWidth()
            ) {
                items(daysOfMonthList.size) { index ->
                    val date = daysOfMonthList[index]
                    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

                    CalendarDateCell(
                        date = date,
                        today = today,
                        isSelected = (date == selectedDate),
                        onDateCellClick = { dateCell ->
                            onDateSelected(dateCell)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = colors.Gray300
            )

            Spacer(modifier = Modifier.height(30.dp))

            when {
                selectedDate == null -> {
                    DiaryDateUnselectedEmptyView()
                }
                diaryExistenceState.hasDiary -> {
                    diaryExistenceState.summary?.let {
                        DiarySummaryView(
                            date = selectedDate,
                            content = it,
                            onSummaryViewClick = { onSummaryViewClick(selectedDate.toString()) }
                        )
                    }
                }
                else -> {
                    NoDiaryEmptyView()
                }
            }
        }

        AddDiaryFloatingButton(
            modifier = Modifier
                .align(BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
                .noRippleClickable { onFloatingBtnClick() },
            onFloatingBtnClick = onFloatingBtnClick
        )
    }
}

@Composable
@Preview
fun DiaryScreenPreview() {
    DiaryScreen(
        padding = PaddingValues(0.dp),
        selectedDate = LocalDate(2025, 1, 1),
        selectedDiary = Diary(LocalDate(2025, 1, 1), "ㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹ"),
        diaryExistenceState = DiaryExistenceModel(true, 1, "", 1),
        onFloatingBtnClick = {},
        onSummaryViewClick = {},
        onDateSelected = {}
    )
}