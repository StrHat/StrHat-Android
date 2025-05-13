package com.konkuk.strhat.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.domain.entity.HomeModel
import com.konkuk.strhat.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    private val _homeModel = MutableStateFlow(
        HomeModel(
            hasDiary = false,
            nickname = "",
            emotion = 1,
            positiveEmotions = listOf("", "", ""),
            stressReliefSuggestion = "",
            stressScore = 0,
            stressLevel = ""
        )
    )
    val homeModel = _homeModel.asStateFlow()

    fun updateHomeModel() {
        viewModelScope.launch {
            homeUseCase()
                .onSuccess {
                    _homeModel.value = it
                }
                .onFailure {
                    Timber.e(it)
                }
        }
    }
}