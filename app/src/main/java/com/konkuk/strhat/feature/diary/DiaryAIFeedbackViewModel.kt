package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DiaryAIFeedbackViewModel @Inject constructor() : ViewModel() {
    private val _diaryAIFeedbackSummary = MutableStateFlow("")
    val diaryAIFeedbackSummary = _diaryAIFeedbackSummary.asStateFlow()

    private val _diaryAIFeedbackPositiveKeywords = MutableStateFlow<List<String>>(emptyList())
    val diaryAIFeedbackPositiveKeywords = _diaryAIFeedbackPositiveKeywords.asStateFlow()

    private val _diaryAIFeedbackNegativeKeywords = MutableStateFlow<List<String>>(emptyList())
    val diaryAIFeedbackNegativeKeywords = _diaryAIFeedbackNegativeKeywords.asStateFlow()

    private val _diaryAIFeedbackRecommendation = MutableStateFlow("")
    val diaryAIFeedbackRecommendation = _diaryAIFeedbackRecommendation.asStateFlow()

    init {
        _diaryAIFeedbackSummary.value = "시험 기간은 항상 스트레스를 주죠. 공부와 시간 부족으로 지쳐가는데도 조금만 더 힘내면 끝이 보인다는 희망으로 자신을 격려하고 계신 모습이 보여요. 지금의 노력과 힘든 시간들이 행복한 미래로 이어질 거라 믿어요."
        _diaryAIFeedbackPositiveKeywords.value = listOf("희망", "노력", "행복")
        _diaryAIFeedbackNegativeKeywords.value = listOf("스트레스", "조급함", "지침")
        _diaryAIFeedbackRecommendation. value = "시험 스트레스를 조금이나마 덜어줄 수 있는 방법으로, 독서와 음악을 조합해보는 건 어떨까요? 좋아하는 음악을 들으며 독서를 즐기면서 마음을 편하게 해보세요. 그러면 조금 더 나아질 거에요."
    }
}