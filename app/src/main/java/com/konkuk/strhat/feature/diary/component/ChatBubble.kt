package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun ChatBubble(
    message: String,
    isSentByUser: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (isSentByUser) colors.Gray100 else colors.SubBlue,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .widthIn(max = 230.dp)
        ) {
            Text(
                text = message,
                style = typography.body3_b_14,
                color = colors.Gray600
            )
        }
    }
}

@Preview
@Composable
private fun ChatBubblePreview() {
    StrHatTheme {
        Column {
            ChatBubble("안녕하세요 송민서님 오늘의 일기 분석 결과 ..", false)
            ChatBubble("나는 요즘 이러이러하고 .. 저러저러하고 .. 그래서 고민이야", true)
        }
    }
}