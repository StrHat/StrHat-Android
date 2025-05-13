package com.konkuk.strhat.domain.entity

data class TokenModel(
    val accessToken: String,
    val refreshToken: String
)