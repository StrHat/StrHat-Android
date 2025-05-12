package com.konkuk.strhat.data.mapper

import android.icu.util.Calendar
import com.konkuk.strhat.domain.entity.MyPageModel
import com.konkuk.strhat.domain.type.GenderType
import com.konkuk.strhat.domain.type.JobType

fun MyPageModel.toDomain(): MyPageModel {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val age = currentYear - birth + 1

    return MyPageModel(
        nickname = nickname,
        birth = age,
        gender = GenderType.fromDisplay(gender).toString(),
        job = JobType.fromDisplay(job).toString(),
        hobbyHealingStyle = hobbyHealingStyle,
        stressReliefStyle = stressReliefStyle,
        personality = personality
    )
}