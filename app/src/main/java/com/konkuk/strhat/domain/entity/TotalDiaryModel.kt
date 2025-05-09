package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class TotalDiaryModel(
    val content: String,
    val diaryId: Int
)
