package com.konkuk.strhat.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.core.network.TokenManager
import com.konkuk.strhat.domain.entity.SignUpModel
import com.konkuk.strhat.domain.type.GenderType
import com.konkuk.strhat.domain.type.JobType
import com.konkuk.strhat.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _progress = MutableStateFlow(0f)
    val progress = _progress.asStateFlow()

    private val _nickName = MutableStateFlow("")
    val nickName = _nickName.asStateFlow()

    private val _selectedYear = MutableStateFlow(0)
    val selectedYear = _selectedYear.asStateFlow()

    private val _selectedOption = MutableStateFlow("")
    val selectedOption = _selectedOption.asStateFlow()

    private val _selectedJob = MutableStateFlow(JobType.STUDENT.displayName)
    val selectedJob = _selectedJob.asStateFlow()

    private val _hobby = MutableStateFlow("")
    val hobby = _hobby.asStateFlow()

    private val _stress = MutableStateFlow("")
    val stress = _stress.asStateFlow()

    private val _personality = MutableStateFlow("")
    val personality = _personality.asStateFlow()

    fun updateNickName(nickName: String) {
        _nickName.value = nickName
    }

    fun updateSelectedYear(year: Int) {
        _selectedYear.value = year
    }

    fun updateSelectedOption(gender: String) {
        _selectedOption.value = gender
    }

    fun updateSelectedJob(job: String) {
        _selectedJob.value = job
    }

    fun updateHobby(hobby: String) {
        _hobby.value = hobby
    }

    fun updateStress(stress: String) {
        _stress.value = stress
    }

    fun updatePersonality(personality: String) {
        _personality.value = personality
    }

    fun updateProgress(currentProgress: Float) {
        _progress.value = currentProgress
    }

    fun requestSignUp(onSuccess: () -> Unit = {}){
        viewModelScope.launch {
            val kakaoId = tokenManager.getKakaoId()
            val gender = GenderType.fromDisplay(selectedOption.value)
            val job = JobType.fromDisplay(selectedJob.value)

            val signUpModel = SignUpModel(
                kakaoId = kakaoId,
                nickname = _nickName.value,
                birth = _selectedYear.value,
                gender = gender,
                job = job,
                hobbyHealingStyle = _hobby.value,
                stressReliefStyle = _stress.value,
                personality = _personality.value
            )

            signUpUseCase(signUpModel)
                .onSuccess { tokenModel ->
                    tokenManager.saveToken(tokenModel.accessToken)
                    tokenManager.saveRefreshToken(tokenModel.refreshToken)
                    onSuccess()
                }
                .onFailure {
                    Timber.e(it, "회원가입 실패")
                }
        }
    }
}