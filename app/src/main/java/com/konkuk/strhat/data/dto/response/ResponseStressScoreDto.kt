package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseStressScoreDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("stressScore")
    val stressScore: Int,
    @SerialName("level")
    val level: String,
    @SerialName("analysis")
    val analysis: String,
    @SerialName("stressScoreDate")
    val stressScoreDate: String
)
