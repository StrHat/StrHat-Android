package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.request.RequestUserInfoDto

fun toRequestUserInfoDto(
    nickname: String,
    birth: Int,
    gender: String,
    job: String
): RequestUserInfoDto {
    return RequestUserInfoDto(
        nickname = nickname,
        birth = birth,
        gender = gender,
        job = job
    )
}