package com.konkuk.strhat.data.mapper

import com.konkuk.strhat.data.dto.request.RequestSignUpDto
import com.konkuk.strhat.domain.entity.SignUpModel

fun SignUpModel.toRequestSignUpDto() = RequestSignUpDto(
    kakaoId = this.kakaoId,
    nickname = this.nickname,
    birth = this.birth,
    gender = this.gender.displayName,
    job = this.job.displayName,
    hobbyHealingStyle = this.hobbyHealingStyle,
    stressReliefStyle = this.stressReliefStyle,
    personality = this.personality
)
