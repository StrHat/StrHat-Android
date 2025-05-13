package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.R
import com.konkuk.strhat.domain.entity.AddDiaryModel
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.domain.entity.EmotionType
import com.konkuk.strhat.domain.entity.TotalDiaryModel
import com.konkuk.strhat.domain.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddDiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    val emotionTypes: List<EmotionType> = listOf(
        EmotionType(R.drawable.ic_strhat_blue, 1),
        EmotionType(R.drawable.ic_strhat_red, 2),
        EmotionType(R.drawable.ic_strhat_gray, 3),
        EmotionType(R.drawable.ic_strhat_yellow, 4),
        EmotionType(R.drawable.ic_strhat_green, 5)
    )

    private val _diaryFeedbackState = MutableStateFlow(DiaryFeedbackModel("", emptyList(), emptyList(), "", 1))
    val diaryFeedbackState: StateFlow<DiaryFeedbackModel> = _diaryFeedbackState

    private val _totalDiaryState = MutableStateFlow(TotalDiaryModel("", 1))
    val totalDiaryState: StateFlow<TotalDiaryModel> = _totalDiaryState

    val diaryContentState = MutableStateFlow("")
    val selectedEmotionIndexState = MutableStateFlow(-1)

    fun postDiary(
        request: AddDiaryModel
    ) {
        viewModelScope.launch {
            try {
                diaryRepository.postDiary(request)
                    .onSuccess { data ->
                        _diaryFeedbackState.update {
                            DiaryFeedbackModel(
                                summary = data.summary,
                                positiveKeywords = data.positiveKeywords,
                                negativeKeywords = data.negativeKeywords,
                                stressReliefSuggestions = data.stressReliefSuggestions,
                                diaryId = data.diaryId
                            )
                        }
                        Timber.tag("save diary").d("일기 저장 성공")
                    }
                    .onFailure { error ->
                        if (error is HttpException) {
                            val errorBody = error.response()?.errorBody()?.string()
                            Timber.tag("save diary").e("$errorBody")
                        } else {
                            Timber.tag("save diary").e(error, "일기 저장 실패")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("save diary").e("일기 저장 서버 통신 오류")
            }
        }
    }

    fun getTotalDiary(
        date: String
    ) {
        viewModelScope.launch {
            try {
                diaryRepository.getTotalDiary(date)
                    .onSuccess { data ->
                        _totalDiaryState.update {
                            TotalDiaryModel(
                                content = data.content,
                                diaryId = data.diaryId
                            )
                        }
                    }
                    .onFailure { error ->
                        if (error is HttpException) {
                            val errorBody = error.response()?.errorBody()?.string()
                            Timber.tag("get total diary").e("$errorBody")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("get total diary").e("일기 전문 보기 조회 오류")
            }
        }
    }
}