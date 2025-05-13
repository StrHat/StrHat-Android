package com.konkuk.strhat.data.dto.response

import com.konkuk.strhat.domain.type.ChatSenderType
import kotlinx.serialization.Serializable

@Serializable
data class ResponseChatHistoryDto(
    val chatMessages: List<ChatHistoryMessage>
)

@Serializable
data class ChatHistoryMessage(
    val content: String,
    val sender: ChatSenderType,
    val createdAt: String
)
