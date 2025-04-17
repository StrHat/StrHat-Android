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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.component.textfield.LongTextField
import com.konkuk.strhat.data.dto.request.RequestAddDiaryDto
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.feature.diary.component.EmotionSelection
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddDiaryRoute(
    padding: PaddingValues,
    navigateToDiaryAIFeedback: (DiaryFeedbackModel) -> Unit,
    viewModel: AddDiaryViewModel = hiltViewModel()
) {
    AddDiaryScreen(
        padding = padding,
        onGetFeedbackBtnClick = {
            navigateToDiaryAIFeedback(viewModel.diaryFeedbackState.value)
        }
    )
}

@Composable
fun AddDiaryScreen(
    padding: PaddingValues,
    onGetFeedbackBtnClick: () -> Unit,
    viewModel: AddDiaryViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var diaryContent by remember { mutableStateOf("") }
    var selectedEmotionIndex by remember { mutableStateOf(-1) }

    val coroutineScope = rememberCoroutineScope()

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
                    text = stringResource(R.string.add_diary_emotion_selection_title),
                    style = typography.head2_r_20,
                    color = colors.MainBlack
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = stringResource(R.string.add_diary_emotion_selection_title_description),
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
                viewModel.emotionTypes.forEachIndexed { index, emotion ->
                    EmotionSelection(
                        emotionStrHat = emotion.iconResId,
                        emotionScore = emotion.score,
                        isSelected = (selectedEmotionIndex == index),
                        onEmotionClick = { selectedEmotionIndex = index }
                    )
                }
            }

            Text(
                text = stringResource(R.string.add_today_diary_title),
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
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp)
            )
        }

        StrHatButton(
            isDisabled = diaryContent.length < 20 || selectedEmotionIndex == -1,
            text = stringResource(R.string.get_feedback_button),
            modifier = Modifier.padding(20.dp),
            onClick = {
                val date = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                val emotionScore = viewModel.emotionTypes[selectedEmotionIndex].score

                viewModel.postDiary(
                    request = RequestAddDiaryDto(
                        date = date,
                        emotion = emotionScore,
                        content = diaryContent
                    )
                )
                coroutineScope.launch {
                    delay(4000) // 테스트 해보니 피드백 생성 주로 3초대 소요
                    onGetFeedbackBtnClick()
                }
            }
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
            modifier = Modifier.background(colors.MainWhite),
            onGetFeedbackBtnClick = {}
        )
    }
}