package com.konkuk.strhat.domain.type

enum class ChatModeType (
    val chatMode: String
) {
    EMPATHY_MODE(
        chatMode = "공감"
    ),
    SOLUTION_MODE(
        chatMode = "해결책"
    )
}