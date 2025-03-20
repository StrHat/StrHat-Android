package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StressScoreViewModel @Inject constructor() : ViewModel() {
    private val _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()

    private val _stressScore = MutableStateFlow(0)
    val stressScore = _stressScore.asStateFlow()

    private val _level = MutableStateFlow("")
    val level = _level.asStateFlow()

    private val _analysis = MutableStateFlow("")
    val analysis = _analysis.asStateFlow()
}