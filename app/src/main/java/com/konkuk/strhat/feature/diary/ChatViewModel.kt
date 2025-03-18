package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import com.konkuk.strhat.feature.diary.state.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()

    fun updateInputText(input: String) {
        _inputText.value = input
    }

    fun sendMessage() {
        val text = _inputText.value
        if (text.isBlank()) return

        val newMessage = ChatMessage(
            message = text,
            isMine = true
        )
        _messages.value = _messages.value + newMessage
        _inputText.value = ""
    }
}