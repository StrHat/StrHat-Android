package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.domain.entity.StressScoreModel
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
class StressScoreViewModel @Inject constructor(
    private val stressScoreRepository: StressScoreRepository
) : ViewModel() {
    private val _stressScoreState = MutableStateFlow(StressScoreModel("", 1, "", "", ""))
    val stressScoreState = _stressScoreState.asStateFlow()

    fun getStressScore(
        date: String
    ) {
        viewModelScope.launch {
            try {
                stressScoreRepository.getStressScore(date)
                    .onSuccess { data ->
                        _stressScoreState.update {
                            StressScoreModel(
                                nickname = data.nickname,
                                stressScore = data.stressScore,
                                level = data.level,
                                analysis = data.analysis,
                                stressScoreDate = data.stressScoreDate
                            )
                        }
                    }
                    .onFailure {
                        if (it is HttpException) {
                            val errorBody = it.response()?.errorBody()?.string()
                            Timber.tag("get stress score").e("$errorBody")
                        } else {
                            Timber.tag("get stress score").e(it, "스트레스 점수 조회 실패")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("get stress score").e("스트레스 점수 조회 오류")
            }
        }
    }
}