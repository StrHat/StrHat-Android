package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun DiaryAIFeedbackKeywordBox(
    positiveKeywords: List<String>,
    feedBackBoxBackgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = feedBackBoxBackgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            positiveKeywords.forEachIndexed { index, keyword ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${index + 1}. ",
                        style = typography.body3_b_14,
                        color = colors.Gray600
                    )
                    Text(
                        text = keyword,
                        style = typography.body3_b_14,
                        color = colors.Gray600
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DiaryAIFeedbackPositiveKeywordBoxPreview() {
    StrHatTheme {
        DiaryAIFeedbackKeywordBox(
            positiveKeywords = listOf("희망", "노력", "행복"),
            feedBackBoxBackgroundColor = colors.SubBlue,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun DiaryAIFeedbackNegativeKeywordBoxPreview() {
    StrHatTheme {
        DiaryAIFeedbackKeywordBox(
            positiveKeywords = listOf("스트레스", "조급함", "지침"),
            feedBackBoxBackgroundColor = colors.Gray100,
            modifier = Modifier.fillMaxWidth()
        )
    }
}