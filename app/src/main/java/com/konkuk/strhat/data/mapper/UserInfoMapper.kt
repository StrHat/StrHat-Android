package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.response.ResponseUserInfoDto
import com.konkuk.strhat.domain.entity.UserInfoModel
import java.util.Calendar

fun ResponseUserInfoDto.toUserInfoModel(): UserInfoModel {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val age = currentYear - this.birth + 1

    return UserInfoModel(
        nickname = this.nickname,
        birth = age,
        gender = this.gender,
        job = this.job,
        hobby = this.hobbyHealingStyle,
        stress = this.stressReliefStyle,
        personality = this.personality
    )
}