package com.konkuk.strhat.domain.entity

data class HomeModel(
    val nickname: String,
    val emotion: Int,
    val positiveEmotions: List<String>,
    val stressReliefSuggestion: String,
    val stressScore: Int,
    val stressLevel: String
)