package com.konkuk.strhat.feature.login.model

data class LoginResult(
    val isSuccess: Boolean = false,
    val isExistingUser: Boolean = false,
    val kakaoId: Long = -1L
)