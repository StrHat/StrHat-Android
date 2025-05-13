package com.konkuk.strhat.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.core.network.TokenManager
import com.konkuk.strhat.domain.entity.MyPageModel
import com.konkuk.strhat.domain.usecase.GetUserInfoUseCase
import com.konkuk.strhat.domain.usecase.PatchHobbyHealingInfoUseCase
import com.konkuk.strhat.domain.usecase.PatchPersonalityInfoUseCase
import com.konkuk.strhat.domain.usecase.PatchStressReliefInfoUseCase
import com.konkuk.strhat.domain.usecase.PatchUserInfoUseCase
import com.konkuk.strhat.domain.usecase.SignOutUseCase
import com.konkuk.strhat.feature.mypage.state.MyWeeklyStressState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userInfoUseCase: GetUserInfoUseCase,
    private val patchUserInfoUseCase: PatchUserInfoUseCase,
    private val patchHobbyHealingInfoUseCase: PatchHobbyHealingInfoUseCase,
    private val patchStressReliefInfoUseCase: PatchStressReliefInfoUseCase,
    private val patchPersonalityInfoUseCase: PatchPersonalityInfoUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _myPageModel = MutableStateFlow(
        MyPageModel(
            nickname = "",
            birth = 0,
            gender = "",
            job = "",
            hobbyHealingStyle = "",
            stressReliefStyle = "",
            personality = ""
        )
    )
    val myPageModel = _myPageModel.asStateFlow()

    private val _myWeeklyStressState = MutableStateFlow(MyWeeklyStressState())
    val myWeeklyStressState = _myWeeklyStressState.asStateFlow()

    init {
        _myWeeklyStressState.update {
            it.copy(
                nickname = "송밍서",
                weeklySummary = "사용자는 완벽주의적이고 내성적인 성향을 가지고 있어서 발표나 의견 충돌, 야근, 인적 손실, 신체적 피로, 그리고 가족 관련 걱정 등 다양한 요인들이 스트레스를 유발했을 것으로 판단됩니다. 이러한 스트레스 요인들이 하나씩 쌓이며 사용자의 마음과 몸에 부담을 주었을 것입니다. 스트레스 관리를 위해 업무에서 완벽을 추구하는 것보다 실수를 수용하고 동료와 의견을 잘 조율하며, 일과 휴식을 균형 있게 유지하는 것이 중요해 보입니다.",
                weeklyStressScores = listOf(10, 2, 3, 4, 6, 7, 9),
                weeklyEmotionScores = listOf(3, 5, 2, 6, 8, 1, 4)
            )
        }
    }

    fun getMyPageModel() {
        viewModelScope.launch {
            userInfoUseCase()
                .onSuccess { user ->
                    _myPageModel.value = MyPageModel(
                        nickname = user.nickname,
                        birth = user.birth,
                        gender = user.gender,
                        job = user.job,
                        hobbyHealingStyle = user.hobby,
                        stressReliefStyle = user.stress,
                        personality = user.personality
                    )
                }
                .onFailure {
                    Timber.e("유저 정보 조회 실패: ${it.message}")
                }
        }
    }

    fun updateNickName(nickname: String) {
        _myPageModel.value = _myPageModel.value.copy(nickname = nickname)
    }

    fun updateBirth(birth: Int) {
        _myPageModel.value = _myPageModel.value.copy(birth = birth)
    }

    fun updateGender(gender: String) {
        _myPageModel.value = _myPageModel.value.copy(gender = gender)
    }

    fun updateJob(job: String) {
        _myPageModel.value = _myPageModel.value.copy(job = job)
    }

    fun updateHealing(healing: String) {
        _myPageModel.value = _myPageModel.value.copy(hobbyHealingStyle = healing)
    }

    fun updateStress(stress: String) {
        _myPageModel.value = _myPageModel.value.copy(stressReliefStyle = stress)
    }

    fun updatePersonality(personality: String) {
        _myPageModel.value = _myPageModel.value.copy(personality = personality)
    }

    fun patchUserInfo(onComplete: () -> Unit) {
        viewModelScope.launch {
            val user = _myPageModel.value
            patchUserInfoUseCase(
                nickname = user.nickname,
                birth = user.birth,
                gender = user.gender,
                job = user.job
            ).onSuccess {
                onComplete()
            }.onFailure {
                Timber.e("유저 정보 수정 실패: ${it.message}")
            }
        }
    }

    fun patchHealingInfo(onComplete: () -> Unit) {
        viewModelScope.launch {
            val healing = _myPageModel.value.hobbyHealingStyle
            patchHobbyHealingInfoUseCase(healing)
                .onSuccess {
                    onComplete()
                }
                .onFailure {
                    Timber.e("유저 정보 수정 실패: ${it.message}")
                }
        }
    }

    fun patchStressInfo(onComplete: () -> Unit) {
        viewModelScope.launch {
            val stress = _myPageModel.value.stressReliefStyle
            patchStressReliefInfoUseCase(stress)
                .onSuccess {
                    onComplete()
                }
                .onFailure {
                    Timber.e("유저 정보 수정 실패: ${it.message}")
                }
        }
    }

    fun patchPersonalityInfo(onComplete: () -> Unit) {
        viewModelScope.launch {
            val personality = _myPageModel.value.personality
            patchPersonalityInfoUseCase(personality)
                .onSuccess {
                    onComplete()
                }
                .onFailure {
                    Timber.e("유저 정보 수정 실패: ${it.message}")
                }
        }
    }

    fun signOut(){
        viewModelScope.launch {
            signOutUseCase()
                .onSuccess {
                    tokenManager.triggerLogout()
                }
                .onFailure {
                    Timber.e("로그아웃 실패: ${it.message}")
                }
        }
    }
}