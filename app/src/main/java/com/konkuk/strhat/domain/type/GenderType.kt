package com.konkuk.strhat.domain.type

enum class GenderType(
    val displayName: String
){
    MALE(displayName = "남자"),
    FEMALE(displayName = "여자");

    companion object {
        fun fromDisplay(display: String): GenderType =
            entries.find { it.displayName == display } ?: MALE
    }
}