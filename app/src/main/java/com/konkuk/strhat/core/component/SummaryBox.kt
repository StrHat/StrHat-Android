package com.konkuk.strhat.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun SummaryBox(
    summary: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(20.dp)
    ) {
        Text(
            text = summary,
            style = typography.body3_b_14,
            color = colors.Gray600
        )
    }
}

@Preview
@Composable
private fun SummaryBoxPreview() {
    StrHatTheme {
        SummaryBox(
            summary = "시험 기간은 항상 스트레스를 주죠. 공부와 시간 부족으로 지쳐가는데도 조금만 더 힘내면 끝이 보인다는 희망으로 자신을 격려하고 계신 모습이 보여요. 지금의 노력과 힘든 시간들이 행복한 미래로 이어질 거라 믿어요.",
            backgroundColor = colors.Gray100,
            modifier = Modifier.fillMaxWidth()
        )
    }
}