package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseHomeDto
import com.konkuk.strhat.domain.entity.HomeModel

fun ResponseHomeDto.toHomeModel(): HomeModel {
    return HomeModel(
        count = this.count,
        region = this.region,
        homePosts = this.homePosts,
        nickname = this.nickname
    )
}