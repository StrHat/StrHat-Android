package com.konkuk.strhat.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.domain.entity.WeeklyStressScoreModel
import com.konkuk.strhat.domain.repository.StressScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyStressGraphViewModel @Inject constructor(
    private val stressScoreRepository: StressScoreRepository
) : ViewModel() {
    private val _weeklyStressScoreModel = MutableStateFlow(WeeklyStressScoreModel("", "", emptyList(), emptyList(), "", ""))
    val weeklyStressScoreModel = _weeklyStressScoreModel.asStateFlow()

    fun getWeeklyStressScore(
        date: String
    ) {
        viewModelScope.launch {
            try {
                stressScoreRepository.getWeeklyStressScore(date)
                    .onSuccess { data ->
                        _weeklyStressScoreModel.update {
                            WeeklyStressScoreModel(
                                nickname = data.nickname,
                                weeklySummary = data.weeklySummary,
                                stressLevels = data.stressLevels,
                                emotionLevels = data.emotionLevels,
                                startDate = data.startDate,
                                endDate = data.endDate
                            )
                        }
                    }
                    .onFailure { throwable ->
                        when {
                            throwable is HttpException && throwable.code() == 404 -> {
                                _weeklyStressScoreModel.update {
                                    it.copy(
                                        weeklySummary = "해당 기간에는 일기를 작성하지 않았습니다.",
                                        stressLevels = emptyList(),
                                        emotionLevels = emptyList()
                                    )
                                }
                            }
                            throwable is HttpException -> {
                                val errorBody = throwable.response()?.errorBody()?.string()
                                Timber.tag("get weekly stress score").e("$errorBody")
                            }
                            else -> {
                                Timber.tag("get weekly stress score").e(throwable, "주간 스트레스 변화 시각화 조회 실패")
                            }
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("get weekly stress score").e("주간 스트레스 변화 시각화 조회 서버 통신 오류")
            }
        }
    }
}