package com.konkuk.strhat.data.repositoryimpl

import com.konkuk.strhat.data.datasource.ChatDataSource
import com.konkuk.strhat.data.mapper.toChatModel
import com.konkuk.strhat.data.mapper.toRequestChatDto
import com.konkuk.strhat.domain.entity.ChatModel
import com.konkuk.strhat.domain.entity.SendChatModel
import com.konkuk.strhat.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDataSource: ChatDataSource
) : ChatRepository {
    override suspend fun postChat(request: SendChatModel, diaryId: Int): Result<ChatModel> =
        runCatching {
            val response = chatDataSource.postChat(request.toRequestChatDto(), diaryId).response
            response.toChatModel()
        }
}