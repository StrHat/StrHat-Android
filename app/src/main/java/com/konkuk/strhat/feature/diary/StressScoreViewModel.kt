package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import com.konkuk.strhat.feature.diary.state.StressScoreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StressScoreViewModel @Inject constructor() : ViewModel() {
    private val _stressScoreState = MutableStateFlow(StressScoreState())
    val stressScoreState = _stressScoreState.asStateFlow()

    init {
        _stressScoreState.update {
            it.copy(
                nickname = "송민서",
                stressScore = 6,
                level = "보통 스트레스 수준",
                analysis = "사용자는 다양한 취향을 가진 다양한 활동을 즐기며 삶을 즐기는 편인데, 시험 기간에는 공부 부담과 시간 부족으로 인한 스트레스를 많이 받는 것으로 보입니다. 여러 전공 과목을 동시에 공부해야 하는 상황에서 과연 배워야 할 것들이 끝이 없다는 생각이 불안을 유발하며, 이로 인해 조급함과 지쳐감을 느끼고 있는 모습입니다. 이외에도 자신이 즐기는 음악 청취나 외향적인 성향의 활동을 쉽게 할 수 없다는 점이 스트레스를 느끼는데 영향을 줄 수 있습니다."
            )
        }
    }
}