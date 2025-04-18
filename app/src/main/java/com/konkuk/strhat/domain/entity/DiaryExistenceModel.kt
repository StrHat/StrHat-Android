package com.konkuk.strhat.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class DiaryExistenceModel(
    val hasDiary: Boolean,
    val emotion: Int?,
    val summary: String?
)
