package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.data.dto.request.RequestChatDto
import com.konkuk.strhat.domain.entity.ChatModel

interface ChatRepository {
    suspend fun postChat(request: RequestChatDto, diaryId: Int): Result<ChatModel>
}