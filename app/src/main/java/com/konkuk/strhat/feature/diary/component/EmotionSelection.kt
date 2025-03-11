package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun EmotionSelection(
    emotionStrHat: Int,
    emotionScore: Int,
    isSelected: Boolean,
    onEmotionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(60.dp)
            .noRippleClickable { onEmotionClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = emotionStrHat),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(8.dp))

        val selectedTextColor = if (isSelected) colors.MainWhite else colors.MainBlack
        val backgroundTextColor = if (isSelected) colors.MainBlue else Transparent

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(30.dp)
                .background(backgroundTextColor, CircleShape)
        ) {
            Text(
                text = emotionScore.toString(),
                style = typography.body4_b_12,
                color = selectedTextColor
            )
        }
    }
}

@Preview
@Composable
fun EmotionSelectionPreview(
    modifier: Modifier = Modifier
) {
    StrHatTheme {
        EmotionSelection(
            emotionStrHat = R.drawable.ic_strhat_blue,
            emotionScore = 1,
            isSelected = true,
            onEmotionClick = {},
            modifier = modifier.background(colors.MainWhite)
        )
    }
}