package com.konkuk.strhat.feature.mypage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.domain.type.DayOfWeekType
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun WeeklyBarChart(
    values: List<Int?>,
    maxBarHeight: Int = 200,
    onBarClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val maxValue = values.filterNotNull().maxOrNull()?.toFloat() ?: 0f

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(colors.Gray100)
            .padding(top = 40.dp, bottom = 10.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(maxBarHeight.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            values.forEachIndexed { index, value ->
                val ratio = if (maxValue > 0) (value?.toFloat() ?: 0f) / maxValue else 0f

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .height(maxBarHeight.dp)
                            .width(24.dp)
                            .noRippleClickable { onBarClick(index) },
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
                                .fillMaxHeight(ratio)
                                .background(colors.MainBlue)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(1.dp)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val dashPx = 6.dp.toPx()
                    val gapPx = 6.dp.toPx()
                    drawLine(
                        color = Color(0xFFD9D9D9),
                        start = Offset(0f, size.height / 2),
                        end = Offset(size.width, size.height / 2),
                        strokeWidth = strokeWidth,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashPx, gapPx), 0f)
                    )
                }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DayOfWeekType.entries.forEach { day ->
                Text(
                    text = day.dayOfWeekType,
                    style = typography.body4_r_12,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyBarChartPreview() {
    val testValues = listOf(10, 2, 3, 4, 6, 7, 9)
    WeeklyBarChart(
        values = testValues,
        onBarClick = {}
    )
}