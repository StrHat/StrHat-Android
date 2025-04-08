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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.feature.diary.ChatViewModel
import com.konkuk.strhat.feature.diary.component.ChatBubble
import com.konkuk.strhat.feature.diary.component.ChatTopBar
import com.konkuk.strhat.feature.diary.state.ChatMessage
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun MyPageChatHistoryRoute(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val messages by viewModel.messages.collectAsState()

    MyPageChatHistoryScreen(
        padding = padding,
        messages = messages,
        popBackStack = popBackStack
    )
}

@Composable
private fun MyPageChatHistoryScreen(
    padding: PaddingValues,
    messages: List<ChatMessage>,
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
                    items(messages) { chatMessage ->
                        ChatBubble(
                            message = chatMessage.message,
                            isSentByUser = chatMessage.isMine
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
            messages = listOf(
                ChatMessage("안녕하세요 송민서님 오늘의 일기 분석 결과 ...", false),
                ChatMessage("나는 요즘 이러이러하고 .. 저러저러하고 .. 그래서 고민이야", true),
                ChatMessage("~~ 를 추천 드려요! ... 해보는 건 어떨까요?", false),
                ChatMessage("...를 하면 힘들지 않을까? 더 좋은 해결법은 없어?", true),
                ChatMessage("...를 하기에는 힘드시군요. 그렇다면 ...는 어떠신가요?", false),
                ChatMessage("그게 좋겠다! 그럼 ... 이 고민은 어쩔까?", true),
                ChatMessage("... 고민에 대해서는 ~~ 방법을 추천 드려요!", false),
                ChatMessage("좋은 해결책이야!", true)
            ),
            popBackStack = {}
        )
    }
}