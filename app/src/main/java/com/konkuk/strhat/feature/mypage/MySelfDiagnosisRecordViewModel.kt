package com.konkuk.strhat.feature.mypage

import androidx.lifecycle.ViewModel
import com.konkuk.strhat.feature.mypage.state.MySelfDiagnosisRecordResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MySelfDiagnosisRecordViewModel @Inject constructor() : ViewModel() {
    private val _mySelfDiagnosisRecordResultState = MutableStateFlow(MySelfDiagnosisRecordResultState())
    val mySelfDiagnosisRecordResultState = _mySelfDiagnosisRecordResultState.asStateFlow()

    init {
        _mySelfDiagnosisRecordResultState.update {
            it.copy(
                nickname = "송민서",
                testType = "PSS",
                stressScore = 10,
                stressLevel = "정상 스트레스 수준",
                stressLevelDescription = "느끼고 있는 스트레스 정도는 정상적인 수준으로,\n심리적으로 안정되어 있습니다.",
                testTypeDescription = "0~13점: 정상 스트레스 수준\n" +
                        "14~16점: 경미한 스트레스 수준\n" +
                        "17~18점: 중간 스트레스 수준\n" +
                        "19점: 높은 스트레스 수준\n\n" + "이 척도는 Cohen, Kamarck과 Mermelstein (1983)의 지각된 스트레스 척도를 한국 실정에 맞게 번안하여 한국 대학생을 대상으로 타당화한 것입니다.\n" +
                        "한국심리학회 홈페이지 또는 KSI한국학술정보 홈페이지에서 원문을 보실 수 있습니다."
            )
        }
    }
}