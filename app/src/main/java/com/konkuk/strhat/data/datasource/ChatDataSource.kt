package com.konkuk.strhat.data.datasource

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestChatDto
import com.konkuk.strhat.data.dto.response.ResponseChatDto
import com.konkuk.strhat.data.dto.response.ResponseChatHistoryDto

interface ChatDataSource {
    suspend fun postChat(request: RequestChatDto, diaryId: Int): BaseResponse<ResponseChatDto>
    suspend fun getChatHistory(diaryId: Int): BaseResponse<ResponseChatHistoryDto>
}