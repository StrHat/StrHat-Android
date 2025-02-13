package com.konkuk.strhat.data.dto.response

import com.konkuk.strhat.domain.entity.HomePostItemModel
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    val count: Int,
    val region: String,
    val homePosts: List<HomePostItemModel>,
    val nickname: String
)
