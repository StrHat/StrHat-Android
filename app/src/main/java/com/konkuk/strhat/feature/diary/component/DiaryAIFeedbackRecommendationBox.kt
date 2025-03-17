package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun DiaryAIFeedbackRecommendationBox(
    diaryAIFeedbackRecommendation: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.SubBlue,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(24.dp)
    ) {
        Text(
            text = diaryAIFeedbackRecommendation,
            style = typography.body3_b_14,
            color = colors.Gray600
        )
    }
}

@Preview
@Composable
private fun DiaryAIFeedbackRecommendationBoxPreview() {
    StrHatTheme {
        DiaryAIFeedbackRecommendationBox(
            diaryAIFeedbackRecommendation = "시험 스트레스를 조금이나마 덜어줄 수 있는 방법으로, 독서와 음악을 조합해보는 건 어떨까요? 좋아하는 음악을 들으며 독서를 즐기면서 마음을 편하게 해보세요.",
            modifier = Modifier.fillMaxWidth()
        )
    }
}