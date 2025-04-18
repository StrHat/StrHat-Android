package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.domain.entity.DiaryExistenceModel
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.domain.repository.DiaryRepository
import com.konkuk.strhat.feature.diary.state.Diary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    private val _selectedDiary = MutableStateFlow<Diary?>(null)
    val selectedDiary = _selectedDiary.asStateFlow()

    private val _diaryExistenceState = MutableStateFlow(DiaryExistenceModel(false, null, null))
    val diaryExistenceState: StateFlow<DiaryExistenceModel> = _diaryExistenceState

    private val _diaryFeedbackState = MutableStateFlow(DiaryFeedbackModel("", emptyList(), emptyList(), ""))
    val diaryFeedbackState: StateFlow<DiaryFeedbackModel> = _diaryFeedbackState

    fun onDateSelected(date: LocalDate) {
        _selectedDate.value = date
        getDiaryExistence(date.toString())
        _selectedDiary.value = null
    }

    private fun getDiaryExistence(
        date: String
    ) {
        viewModelScope.launch {
            try {
                diaryRepository.getDiaryExistence(date)
                    .onSuccess { data ->
                        _diaryExistenceState.update {
                            DiaryExistenceModel(
                                hasDiary = data.hasDiary,
                                emotion = data.emotion,
                                summary = data.summary
                            )
                        }
                    }
                    .onFailure {
                        if (it is HttpException) {
                            val errorBody = it.response()?.errorBody()?.string()
                            Timber.tag("get diary existence").e("$errorBody")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("get diary existence").e("일기 존재 여부 확인 조회 오류")
            }
        }
    }

    fun getDiaryFeedback(
        date: String
    ) {
        viewModelScope.launch {
            try {
                diaryRepository.getDiaryFeedback(date)
                    .onSuccess { data ->
                        _diaryFeedbackState.update {
                            DiaryFeedbackModel(
                                summary = data.summary,
                                positiveKeywords = data.positiveKeywords,
                                negativeKeywords = data.negativeKeywords,
                                stressReliefSuggestions = data.stressReliefSuggestions
                            )
                        }
                    }
                    .onFailure {
                        if (it is HttpException) {
                            val errorBody = it.response()?.errorBody()?.string()
                            Timber.tag("get diary feedback").e("$errorBody")
                        } else {
                            Timber.tag("get diary feedback").e(it, "피드백 조회 실패")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("get diary feedback").e("피드백 조회 서버 통신 오류")
            }
        }
    }
}