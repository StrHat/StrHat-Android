package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun CalendarDayOfWeekCell(
    modifier: Modifier = Modifier
) {
    val dayOfWeekList = listOf(
        stringResource(R.string.day_of_week_sunday),
        stringResource(R.string.day_of_week_monday),
        stringResource(R.string.day_of_week_tuesday),
        stringResource(R.string.day_of_week_wednesday),
        stringResource(R.string.day_of_week_thursday),
        stringResource(R.string.day_of_week_friday),
        stringResource(R.string.day_of_week_saturday),
    )

    Row(
        modifier = modifier.fillMaxWidth()
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
}