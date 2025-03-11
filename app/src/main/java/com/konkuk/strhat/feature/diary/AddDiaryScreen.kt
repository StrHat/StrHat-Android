package com.konkuk.strhat.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.component.textfield.LongTextField
import com.konkuk.strhat.feature.diary.component.EmotionSelection
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun AddDiaryRoute(
    padding: PaddingValues
) {
    AddDiaryScreen(
        padding = padding
    )
}

@Composable
fun AddDiaryScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    var diaryContent by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(top = 70.dp)
            .imePadding()
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .background(colors.MainWhite)
        ) {
            PageDescriptionSection(
                titleResId = R.string.add_diary_screen_title,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "오늘의 감정 선택하기",
                    style = typography.head2_r_20,
                    color = colors.MainBlack
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "(클수록 좋음에 해당)",
                    style = typography.body2_r_15,
                    color = colors.Gray500,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val emotionStrHatList = listOf(
                    R.drawable.ic_strhat_blue,
                    R.drawable.ic_strhat_red,
                    R.drawable.ic_strhat_gray,
                    R.drawable.ic_strhat_yellow,
                    R.drawable.ic_strhat_green
                )
                val emotionScoreList = listOf(1, 2, 3, 4, 5)

                emotionStrHatList.forEachIndexed { index, emotionIcon ->
                    EmotionSelection(
                        emotionStrHat = emotionIcon,
                        emotionScore = emotionScoreList[index]
                    )
                }
            }

            Text(
                text = "오늘의 일기 작성하기",
                style = typography.head2_r_20,
                color = colors.MainBlack,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            LongTextField(
                value = diaryContent,
                onValueChange = { diaryContent = it },
                hint = stringResource(R.string.textfield_diary),
                maxLength = 1500,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }

        StrHatButton(
            isDisabled = diaryContent.length < 20,
            text = "피드백 받기",
            modifier = Modifier.padding(20.dp),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun AddDiaryScreenPreview(
    modifier: Modifier = Modifier
) {
    StrHatTheme {
        AddDiaryScreen(
            padding = PaddingValues(),
            modifier = Modifier.background(colors.MainWhite)
        )
    }
}