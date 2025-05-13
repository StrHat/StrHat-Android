package com.konkuk.strhat.domain.repository

import com.konkuk.strhat.domain.entity.ChatModel
import com.konkuk.strhat.domain.entity.SendChatModel

interface ChatRepository {
    suspend fun postChat(request: SendChatModel, diaryId: Int): Result<ChatModel>
}