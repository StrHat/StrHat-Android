package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseHomeDto
import com.konkuk.strhat.domain.entity.HomeModel

fun ResponseHomeDto.toHomeModel() = HomeModel(
    count = count,
    region = region,
    homePosts = homePosts,
    nickname = nickname
)