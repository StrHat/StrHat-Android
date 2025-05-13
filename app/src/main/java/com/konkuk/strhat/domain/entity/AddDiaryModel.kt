package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class AddDiaryModel(
    val date: String,
    val emotion: Int,
    val content: String
)
