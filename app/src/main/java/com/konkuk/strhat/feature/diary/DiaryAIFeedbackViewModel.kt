package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import com.konkuk.strhat.feature.diary.state.DiaryAIFeedbackState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DiaryAIFeedbackViewModel @Inject constructor() : ViewModel() {
    private val _selectedDate = MutableStateFlow("")
    val selectedDate = _selectedDate.asStateFlow()

    private val _diaryAIFeedbackState = MutableStateFlow(DiaryAIFeedbackState())
    val diaryAIFeedbackState = _diaryAIFeedbackState.asStateFlow()

    private val _totalDiary = MutableStateFlow("")
    val totalDiary = _totalDiary.asStateFlow()

    init {
        _diaryAIFeedbackState.update {
            it.copy(
                diaryAIFeedbackSummary = "시험 기간은 항상 스트레스를 주죠. 공부와 시간 부족으로 지쳐가는데도 조금만 더 힘내면 끝이 보인다는 희망으로 자신을 격려하고 계신 모습이 보여요. 지금의 노력과 힘든 시간들이 행복한 미래로 이어질 거라 믿어요.",
                diaryAIFeedbackPositiveKeywords = listOf("희망", "노력", "행복"),
                diaryAIFeedbackNegativeKeywords = listOf("스트레스", "조급함", "지침"),
                diaryAIFeedbackRecommendation = "시험 스트레스를 조금이나마 덜어줄 수 있는 방법으로, 독서와 음악을 조합해보는 건 어떨까요? 좋아하는 음악을 들으며 독서를 즐기면서 마음을 편하게 해보세요. 그러면 조금 더 나아질 거에요."
            )
        }
        _totalDiary.value = "3월 17일 일기 전문 예시입니다. 시험 기간에 관한 일기입니다."
    }
}