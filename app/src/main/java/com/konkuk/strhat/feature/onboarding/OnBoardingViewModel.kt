package com.konkuk.strhat.feature.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {
    private val _nickName = MutableStateFlow("")
    val nickName = _nickName.asStateFlow()

    private val _selectedYear = MutableStateFlow(0)
    val selectedYear = _selectedYear.asStateFlow()

    private val _selectedOption = MutableStateFlow("")
    val selectedOption = _selectedOption.asStateFlow()

    private val _hobbyText = MutableStateFlow("")
    val hobbyText = _hobbyText.asStateFlow()

    fun updateNickName(nickName: String) {
        _nickName.value = nickName
    }

    fun updateSelectedYear(year: Int) {
        _selectedYear.value = year
    }

    fun updateSelectedOption(gender: String) {
        _selectedOption.value = gender
    }

    fun updateHobby(hobby: String) {
        _hobbyText.value = hobby
    }
}