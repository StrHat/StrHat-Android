package com.konkuk.strhat.domain.type

enum class GenderType(
    val type: String
){
    MALE(type = "남성"),
    FEMALE(type = "여성");

    companion object{
        fun toGenderType(gender: String): String =
            entries.find { it.name.equals(gender, ignoreCase = true) }?.type?:MALE.type
    }
}