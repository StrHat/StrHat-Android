package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.request.RequestChatDto
import com.konkuk.strhat.data.dto.response.ResponseChatDto
import com.konkuk.strhat.domain.entity.ChatModel
import com.konkuk.strhat.domain.entity.SendChatModel

fun ResponseChatDto.toChatModel() = ChatModel(
    message = message
)

fun SendChatModel.toRequestChatDto() = RequestChatDto(
    userMessage = userMessage,
    chatMode = chatMode
)