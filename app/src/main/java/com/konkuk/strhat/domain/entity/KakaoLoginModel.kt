package com.konkuk.strhat.domain.entity

data class KakaoLoginModel(
    val userExists: Boolean,
    val kakaoId: Long,
    val authorization: String?,
    val refreshToken: String?
)