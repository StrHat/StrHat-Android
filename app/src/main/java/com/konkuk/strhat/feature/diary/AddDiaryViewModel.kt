package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.domain.entity.EmotionType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDiaryViewModel @Inject constructor() : ViewModel() {

    val emotionTypes: List<EmotionType> = listOf(
        EmotionType(R.drawable.ic_strhat_blue, 1),
        EmotionType(R.drawable.ic_strhat_red, 2),
        EmotionType(R.drawable.ic_strhat_gray, 3),
        EmotionType(R.drawable.ic_strhat_yellow, 4),
        EmotionType(R.drawable.ic_strhat_green, 5)
    )
}