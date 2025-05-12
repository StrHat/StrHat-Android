package com.konkuk.strhat.data.datasourceimpl

import com.konkuk.strhat.data.datasource.ChatDataSource
import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestChatDto
import com.konkuk.strhat.data.dto.response.ResponseChatDto
import com.konkuk.strhat.data.service.ChatService
import javax.inject.Inject

class ChatDataSourceImpl @Inject constructor(
    private val chatService: ChatService
) : ChatDataSource {
    override suspend fun postChat(request: RequestChatDto, diaryId: Int): BaseResponse<ResponseChatDto> =
        chatService.postChat(request, diaryId)
}