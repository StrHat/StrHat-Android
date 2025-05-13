package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.domain.entity.ChatHistoryModel
import com.konkuk.strhat.domain.type.ChatSenderType
import com.konkuk.strhat.feature.diary.ChatViewModel
import com.konkuk.strhat.feature.diary.component.ChatBubble
import com.konkuk.strhat.feature.diary.component.ChatTopBar
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun MyPageChatHistoryRoute(
    padding: PaddingValues,
    diaryId: Int,
    popBackStack: () -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val chatHistoryModel by viewModel.chatHistoryModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getChatHistory(diaryId)
    }

    MyPageChatHistoryScreen(
        padding = padding,
        chatHistoryModel = chatHistoryModel,
        popBackStack = popBackStack
    )
}

@Composable
private fun MyPageChatHistoryScreen(
    padding: PaddingValues,
    chatHistoryModel: ChatHistoryModel,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(padding)
    ) {
        Column {
            ChatTopBar(
                showQuitBtn = false
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    state = lazyListState,
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(chatHistoryModel.chatMessages) { chatMessage ->
                        ChatBubble(
                            message = chatMessage.content,
                            isSentByUser = chatMessage.sender == ChatSenderType.USER
                        )
                    }
                }
                StrHatButton(
                    text = stringResource(R.string.my_page_chat_history_quit_button),
                    onClick = {
                        popBackStack()
                    },
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MyPageChatHistoryScreenPreview() {
    StrHatTheme {
        MyPageChatHistoryScreen(
            padding = PaddingValues(),
            chatHistoryModel = ChatHistoryModel(emptyList()),
            popBackStack = {}
        )
    }
}