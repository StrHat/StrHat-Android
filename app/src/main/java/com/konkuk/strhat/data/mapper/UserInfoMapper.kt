package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseUserInfoDto
import com.konkuk.strhat.domain.entity.UserInfoModel

fun ResponseUserInfoDto.toUserInfoModel(): UserInfoModel {
    return UserInfoModel(
        nickname = this.nickname,
        birth = this.birth,
        gender = this.gender,
        job = this.job,
        hobby = this.hobbyHealingStyle,
        stress = this.stressReliefStyle,
        personality = this.personality
    )
}