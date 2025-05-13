package com.konkuk.strhat.domain.type

import kotlinx.serialization.Serializable

@Serializable
enum class ChatSenderType {
    USER, CHAT_BOT
}