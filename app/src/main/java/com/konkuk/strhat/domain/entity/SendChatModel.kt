package com.konkuk.strhat.domain.entity

import com.konkuk.strhat.domain.type.ChatModeType
import kotlinx.serialization.Serializable

@Serializable
data class SendChatModel(
    val userMessage: String,
    val chatMode: ChatModeType
)
