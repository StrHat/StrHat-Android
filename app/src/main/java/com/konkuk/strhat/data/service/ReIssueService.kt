package com.konkuk.strhat.data.service

import com.konkuk.strhat.data.dto.request.RequestReissueTokenDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ReIssueService {
    @POST("/api/v1/users/reissue-token")
    fun reissueToken(
        @Body reissueTokenRequest: RequestReissueTokenDto
    ): Call<Unit>
}