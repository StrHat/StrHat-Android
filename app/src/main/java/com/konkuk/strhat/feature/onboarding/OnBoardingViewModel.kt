package com.konkuk.strhat.feature.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {
    private val _progress = MutableStateFlow(1 / 6f)
    val progress = _progress.asStateFlow()

    private val _nickName = MutableStateFlow("")
    val nickName = _nickName.asStateFlow()

    private val _selectedYear = MutableStateFlow(0)
    val selectedYear = _selectedYear.asStateFlow()

    fun updateNickName(nickName: String) {
        _nickName.value = nickName
    }

    fun updateSelectedYear(year: Int) {
        _selectedYear.value = year
    }

    fun updateProgress() {
        _progress.value += 1 / 6f
    }
}