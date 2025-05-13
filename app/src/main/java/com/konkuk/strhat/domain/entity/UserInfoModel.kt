package com.konkuk.strhat.domain.entity

data class UserInfoModel(
    val nickname: String,
    val birth: Int,
    val gender: String,
    val job: String,
    val hobby: String,
    val stress: String,
    val personality: String
)