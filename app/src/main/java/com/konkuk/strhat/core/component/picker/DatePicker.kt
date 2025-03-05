package com.konkuk.strhat.core.component.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Horizontal
import androidx.compose.ui.Alignment.Vertical
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kez.picker.Picker
import com.kez.picker.PickerState
import com.konkuk.strhat.core.util.time.currentDate
import com.konkuk.strhat.core.util.time.currentDateTime
import com.konkuk.strhat.core.util.time.toDate
import com.konkuk.strhat.core.util.time.toKoreanDay
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus

@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    startDateTime: LocalDateTime = currentDateTime,
    onDateTimeSelected: (LocalDateTime?) -> Unit = {},
    datePickerState: PickerState<String> = remember {
        PickerState(
            "${startDateTime.year}년 " +
            "${startDateTime.monthNumber}월 " +
            "${startDateTime.dayOfMonth}일 " +
            "${startDateTime.dayOfWeek.name.toKoreanDay()}요일"
        )
    },
    dateItems: List<String> = remember {
        val startDate = currentDate.minus(DatePeriod(years = 1))
        val endDate = currentDate

        if (startDate > endDate) emptyList()
        else {
            val totalDays = endDate.toEpochDays() - startDate.toEpochDays() + 1
            (0 until totalDays).map { offset ->
                val date = startDate.plus(DatePeriod(days = offset))
                "${date.year}년 " +
                "${date.monthNumber}월 " +
                "${date.dayOfMonth}일 " +
                "${date.dayOfWeek.name.toKoreanDay()}요일"
            }
        }
    },
    visibleItemsCount: Int = 3,
    itemPadding: PaddingValues = PaddingValues(top = 11.dp, bottom = 15.dp, start = 5.dp, end = 5.dp),
    textStyle: TextStyle = typography.head2_b_20.copy(color = colors.Gray300),
    selectedTextStyle: TextStyle = typography.head2_b_20.copy(color = colors.Gray600),
    fadingEdgeGradient: Brush = Brush.verticalGradient(
        0f to Transparent,
        0.5f to colors.Gray600,
        1f to Transparent
    ),
    horizontalAlignment: Horizontal = Alignment.CenterHorizontally,
    verticalAlignment: Vertical = Alignment.CenterVertically
) {
    val density = LocalDensity.current
    val itemHeight = with(density) {
        selectedTextStyle.fontSize.toDp() +
                itemPadding.calculateTopPadding() +
                itemPadding.calculateBottomPadding()
    }
    LaunchedEffect(datePickerState.selectedItem) {
        try {
            val selectedDate = datePickerState.selectedItem.toDate()
            val selectedDateTime = LocalDateTime(selectedDate, LocalTime(0, 0))
            onDateTimeSelected(selectedDateTime)
        } catch (e: Exception) {
            onDateTimeSelected(null)
        }
    }
    Row(
        modifier = modifier.background(colors.MainWhite),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .background(color = colors.MainWhite, shape = RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .background(color = colors.MainBlue, shape = RoundedCornerShape(12.dp))
            )
            Picker(
                state = datePickerState,
                items = dateItems,
                startIndex = dateItems.indexOf(datePickerState.selectedItem),
                visibleItemsCount = visibleItemsCount,
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                dividerColor = Transparent,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient,
                horizontalAlignment = horizontalAlignment,
                itemTextAlignment = verticalAlignment,
                isInfinity = false
            )
        }
    }
}