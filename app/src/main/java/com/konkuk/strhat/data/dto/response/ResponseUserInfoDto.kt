package com.konkuk.strhat.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfoDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("birth")
    val birth: Int,
    @SerialName("gender")
    val gender: String,
    @SerialName("job")
    val job: String,
    @SerialName("hobbyHealingStyle")
    val hobbyHealingStyle: String,
    @SerialName("stressReliefStyle")
    val stressReliefStyle: String,
    @SerialName("personality")
    val personality: String
)