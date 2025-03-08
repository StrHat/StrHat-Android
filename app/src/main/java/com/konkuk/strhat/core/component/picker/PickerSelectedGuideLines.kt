package com.konkuk.strhat.core.component.picker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import com.konkuk.strhat.ui.theme.defaultStrHatColors

@Composable
fun GuideLines(
    visibleItemsCount: Int,
    itemHeightDp: Dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeightDp * visibleItemsCount)
    ) {
        val borderHeight = itemHeightDp.toPx()
        val center = size.height / 2
        drawLine(
            color = defaultStrHatColors.Gray400,
            start = Offset(0f, center - borderHeight / 2),
            end = Offset(size.width, center - borderHeight / 2),
            strokeWidth = 0.5f
        )
        drawLine(
            color = defaultStrHatColors.Gray400,
            start = Offset(0f, center + borderHeight / 2),
            end = Offset(size.width, center + borderHeight / 2),
            strokeWidth = 0.5f
        )
    }
}