package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseChatDto
import com.konkuk.strhat.domain.entity.ChatModel

fun ResponseChatDto.toChatModel() = ChatModel(
    message = message
)