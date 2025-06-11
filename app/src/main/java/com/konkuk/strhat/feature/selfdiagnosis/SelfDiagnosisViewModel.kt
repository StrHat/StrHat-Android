package com.konkuk.strhat.feature.selfdiagnosis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.domain.entity.SelfDiagnosisItem
import com.konkuk.strhat.domain.entity.SelfDiagnosisModel
import com.konkuk.strhat.domain.entity.SelfDiagnosisResultModel
import com.konkuk.strhat.domain.repository.SelfDiagnosisRepository
import com.konkuk.strhat.feature.selfdiagnosis.state.SelfDiagnosisResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SelfDiagnosisViewModel @Inject constructor(
    private val selfDiagnosisRepository: SelfDiagnosisRepository
) : ViewModel() {
    private val _selfDiagnosisResultState = MutableStateFlow(SelfDiagnosisResultState())
    val selfDiagnosisResultState = _selfDiagnosisResultState.asStateFlow()

    init {
        _selfDiagnosisResultState.update {
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

    private val _selfDiagnosisListModel = MutableStateFlow<List<SelfDiagnosisItem>>(emptyList())
    val selfDiagnosisListModel = _selfDiagnosisListModel.asStateFlow()

    private val _selfDiagnosisResultModel = MutableStateFlow(SelfDiagnosisResultModel("", 1, "", ""))
    val selfDiagnosisResultModel: StateFlow<SelfDiagnosisResultModel> = _selfDiagnosisResultModel

    private val _selfDiagnosisModel = MutableStateFlow(SelfDiagnosisModel("", 1))
    val selfDiagnosisModel: StateFlow<SelfDiagnosisModel> = _selfDiagnosisModel

    fun getSelfDiagnosisQuestionList(
        type: String
    ) {
        viewModelScope.launch {
            try {
                selfDiagnosisRepository.getSelfDiagnosisQuestionList(type)
                    .onSuccess { data ->
                        _selfDiagnosisListModel.update {
                            data
                        }
                    }
                    .onFailure {
                        if (it is HttpException) {
                            val errorBody = it.response()?.errorBody()?.string()
                            Timber.tag("get self diagnosis question list").e("$errorBody")
                        } else {
                            Timber.tag("get self diagnosis question list").e(it, "자가진단 문제 데이터 조회 실패")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("get self diagnosis question list").e("자가진단 문제 데이터 조회 서버 통신 오류")
            }
        }
    }

    fun getSelfDiagnosisResult(
        date: String,
        type: String
    ) {
        viewModelScope.launch {
            try {
                selfDiagnosisRepository.getSelfDiagnosisResult(date, type)
                    .onSuccess { data ->
                        _selfDiagnosisResultModel.update {
                            SelfDiagnosisResultModel(
                                nickname = data.nickname,
                                score = data.score,
                                type = data.type,
                                selfDiagnosisLevel = data.selfDiagnosisLevel
                            )
                        }
                    }
                    .onFailure {
                        if (it is HttpException) {
                            val errorBody = it.response()?.errorBody()?.string()
                            Timber.tag("get self diagnosis result").e("$errorBody")
                        } else {
                            Timber.tag("get self diagnosis result").e(it, "자가진단 결과 조회 실패")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("get self diagnosis result").e("자가진단 문제 데이터 조회 서버 통신 오류")
            }
        }
    }

    fun postSelfDiagnosis(
        request: SelfDiagnosisModel
    ) {
        viewModelScope.launch {
            try {
                selfDiagnosisRepository.postSelfDiagnosis(request)
                    .onSuccess {

                    }
                    .onFailure {
                        if (it is HttpException) {
                            val errorBody = it.response()?.errorBody()?.string()
                            Timber.tag("post self diagnosis").e("$errorBody")
                        } else {
                            Timber.tag("post self diagnosis").e(it, "자가진단 결과 저장 실패")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("post self diagnosis").e("자가진단 결과 저장 서버 통신 오류")
            }
        }
    }
}