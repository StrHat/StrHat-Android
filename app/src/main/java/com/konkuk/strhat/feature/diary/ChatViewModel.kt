package com.konkuk.strhat.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.strhat.domain.entity.ChatHistoryModel
import com.konkuk.strhat.domain.entity.ChatModel
import com.konkuk.strhat.domain.entity.SendChatModel
import com.konkuk.strhat.domain.repository.ChatRepository
import com.konkuk.strhat.feature.diary.state.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages = _messages.asStateFlow()

    private val _chatHistoryModel = MutableStateFlow(ChatHistoryModel(emptyList()))
    val chatHistoryModel: StateFlow<ChatHistoryModel> = _chatHistoryModel

    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()

    private val _chatState = MutableStateFlow(ChatModel(""))
    val chatState: StateFlow<ChatModel> = _chatState

    fun updateInputText(input: String) {
        _inputText.value = input
    }

    fun postChat(
        request: SendChatModel,
        diaryId: Int
    ) {
        viewModelScope.launch {
            try {
                chatRepository.postChat(request, diaryId)
                    .onSuccess { data ->
                        _chatState.update {
                            ChatModel(
                                message = data.message
                            )
                        }
                        _messages.update {
                            it + ChatMessage(message = data.message, isMine = false)
                        }
                        Timber.tag("post chat").d("대화 전송 성공")
                    }
                    .onFailure { error ->
                        if (error is HttpException) {
                            val errorBody = error.response()?.errorBody()?.string()
                            Timber.tag("post chat").e("$errorBody")
                        } else {
                            Timber.tag("post chat").e(error, "대화 전송 실패")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("post chat").e("대화하기 서버 통신 오류")
            }
        }
    }

    fun getChatHistory(
        diaryId: Int
    ) {
        viewModelScope.launch {
            try {
                chatRepository.getChatHistory(diaryId)
                    .onSuccess { data ->
                        _chatHistoryModel.update {
                            ChatHistoryModel(
                                chatMessages = data.chatMessages
                            )
                        }
                    }
                    .onFailure {
                        if (it is HttpException) {
                            val errorBody = it.response()?.errorBody()?.string()
                            Timber.tag("get chat history").e("$errorBody")
                        }
                    }
            } catch (e: Exception) {
                Timber.tag("get chat history").e("대화 기록 조회 오류")
            }
        }
    }

    fun sendMessage() {
        val text = _inputText.value
        if (text.isBlank()) return

        val newMessage = ChatMessage(
            message = text,
            isMine = true
        )
        _messages.value += newMessage
        _inputText.value = ""
    }
}