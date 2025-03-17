package com.konkuk.strhat.feature.home

import androidx.lifecycle.ViewModel
import com.konkuk.strhat.domain.entity.HomeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
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
        _homeModel.value =
            HomeModel(
                hasDiary = false,
                nickname = "밍서",
                emotion = 3,
                positiveEmotions = listOf("행복", "즐거움", "기쁨"),
                stressReliefSuggestion = "시험 스트레스를 조금이나마 덜어줄 수 있는 방법으로, 독서와 음악을 조합해보는 건 어떨까요? 좋아하는 음악을 들으며 독서를 즐기면서 마음을 편하게 해보세요.",
                stressScore = 8,
                stressLevel = "높은 스트레스 수준"
            )
    }
}