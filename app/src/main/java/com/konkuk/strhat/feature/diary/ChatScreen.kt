package com.konkuk.strhat.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.dialog.StrHatDialog
import com.konkuk.strhat.domain.entity.SendChatModel
import com.konkuk.strhat.domain.type.ChatModeType
import com.konkuk.strhat.feature.diary.component.ChatBubble
import com.konkuk.strhat.feature.diary.component.ChatTextFieldRow
import com.konkuk.strhat.feature.diary.component.ChatTopBar
import com.konkuk.strhat.feature.diary.state.ChatMessage
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun ChatRoute(
    padding: PaddingValues,
    diaryId: Int,
    date: String,
    chatMode: ChatModeType,
    navigateToTodayStressScore: (String) -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val messages by viewModel.messages.collectAsState()
    val inputText by viewModel.inputText.collectAsState()

    ChatScreen(
        padding = padding,
        date = date,
        messages = messages,
        inputText = inputText,
        onTextChange = { viewModel.updateInputText(it) },
        onSendClick = {
            viewModel.sendMessage()
            viewModel.postChat(
                request = SendChatModel(
                    userMessage = inputText,
                    chatMode = chatMode
                ),
                diaryId = diaryId
            )
        },
        navigateToTodayStressScore = navigateToTodayStressScore
    )
}

@Composable
private fun ChatScreen(
    padding: PaddingValues,
    date: String,
    messages: List<ChatMessage>,
    inputText: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
    navigateToTodayStressScore: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    var showChatQuitDialog by remember { mutableStateOf(false) }

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            lazyListState.animateScrollToItem(messages.size - 1)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(padding)
    ) {
        Column {
            ChatTopBar(
                showQuitBtn = true,
                onChatQuitBtnClick = {
                    showChatQuitDialog = true
                }
            )
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
            ChatTextFieldRow(
                message = inputText,
                onTextChange = onTextChange,
                onSendClick = onSendClick,
                modifier = Modifier
                    .padding(20.dp)
                    .imePadding()
            )
        }
    }

    if (showChatQuitDialog) {
        Dialog(
            onDismissRequest = { showChatQuitDialog = false }
        ) {
            StrHatDialog(
                titleResId = R.string.dialog_chat_title,
                imageResId = R.drawable.ic_strhat_dialog_yellow_red_green,
                imageRatio = 1f / 1f,
                descriptionResId = R.string.dialog_chat_description,
                onConfirmButtonClick = {
                    showChatQuitDialog = false
                    navigateToTodayStressScore(date)
                },
                onDismissButtonClick = { showChatQuitDialog = false },
                modifier = Modifier.padding(horizontal = 40.dp)
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    StrHatTheme {
        ChatScreen(
            padding = PaddingValues(),
            date = "2025-05-01",
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
            inputText = "",
            onTextChange = {},
            onSendClick = {},
            navigateToTodayStressScore = {}
        )
    }
}