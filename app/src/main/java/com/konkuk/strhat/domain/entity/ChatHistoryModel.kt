package com.konkuk.strhat.domain.entity

import com.konkuk.strhat.data.dto.response.ChatHistoryMessage
import kotlinx.serialization.Serializable

@Serializable
data class ChatHistoryModel(
    val chatMessages: List<ChatHistoryMessage>
)