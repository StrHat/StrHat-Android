package com.konkuk.strhat.domain.entity

data class HomeModel(
    val count: Int,
    val region: String,
    val homePosts: List<HomePostItemModel>,
    val nickname: String
)