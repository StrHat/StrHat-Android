package com.konkuk.strhat.data.dto.request

import com.konkuk.strhat.domain.type.ChatModeType
import kotlinx.serialization.Serializable

@Serializable
data class RequestChatDto(
    val userMessage: String,
    val chatMode: ChatModeType
)
