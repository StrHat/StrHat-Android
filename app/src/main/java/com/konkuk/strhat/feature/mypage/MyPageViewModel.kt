package com.konkuk.strhat.feature.mypage

import androidx.lifecycle.ViewModel
import com.konkuk.strhat.domain.entity.MyPageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor() : ViewModel() {
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

    fun updateMyPageModel() {
        _myPageModel.value =
            MyPageModel(
                nickname = "송밍서",
                birth = 2001,
                gender = "MALE",
                job = "STUDENT",
                hobbyHealingStyle = "1. 혼자만의 시간을 보내며 독서를 좋아함. ...",
                stressReliefStyle = "1. 집 앞 공원에 나가 찬 공기를 ...",
                personality = "1. 내성적인 편임 ..."
            )
    }
}