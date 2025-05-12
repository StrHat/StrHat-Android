package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.base.BaseResponse
import com.konkuk.strhat.data.dto.request.RequestChatDto
import com.konkuk.strhat.data.dto.response.ResponseChatDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService {
    @POST("/api/v1/diaries/{diaryId}/chat")
    suspend fun postChat(
        @Body request: RequestChatDto,
        @Path("diaryId") diaryId: Int
    ): BaseResponse<ResponseChatDto>
}