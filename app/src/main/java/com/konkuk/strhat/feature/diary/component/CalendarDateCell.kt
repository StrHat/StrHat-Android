package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.datetime.LocalDate

@Composable
fun CalendarDateCell(
    date: LocalDate?,
    today: LocalDate,
    isSelected: Boolean,
    onDateCellClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .noRippleClickable {
                date?.let (onDateCellClick)
            },
        contentAlignment = Alignment.Center
    ) {
        if (date != null) {
            val textColor = when {
                date == today -> colors.MainBlue
                date < today  -> colors.MainBlack
                else          -> colors.Gray300
            }

            val selectedTextColor = if (isSelected) colors.MainWhite else textColor
            val backgroundTextColor = if (isSelected) colors.MainBlue else Transparent

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(backgroundTextColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    style = typography.body1_m_16,
                    color = selectedTextColor
                )
            }
        }
    }
}