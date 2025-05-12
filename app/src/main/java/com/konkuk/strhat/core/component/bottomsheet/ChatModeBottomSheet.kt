package com.konkuk.strhat.core.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.bottomsheet.draghandle.BottomSheetDragHandle
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.domain.type.ChatModeType
import com.konkuk.strhat.feature.diary.component.HeartButton
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatModeBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onChatModeSelected: (String?) -> Unit,
    navigateToChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedMode by remember { mutableStateOf<String?>(null) }

    if (isVisible) {
        Box(
            modifier = modifier
                .wrapContentHeight()
        ) {
            ModalBottomSheet(
                onDismissRequest = { onDismiss() },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = colors.MainWhite,
                modifier = Modifier.fillMaxWidth(),
                dragHandle = {
                    BottomSheetDragHandle()
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.chat_select_chat_mode),
                        style = typography.title1_b_18,
                        color = colors.MainBlack
                    )
                    Text(
                        text = stringResource(R.string.chat_select_chat_mode_description),
                        style = typography.body3_m_14,
                        color = colors.Gray500
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HeartButton(
                            modeText = ChatModeType.EMPATHY_MODE.chatMode,
                            isModeSelected = (selectedMode == ChatModeType.EMPATHY_MODE.chatMode),
                            onModeClick = { mode, _ ->
                                selectedMode = if (selectedMode == ChatModeType.EMPATHY_MODE.chatMode) null else ChatModeType.EMPATHY_MODE.chatMode
                            }
                        )

                        Spacer(modifier = Modifier.width(50.dp))

                        HeartButton(
                            modeText = ChatModeType.SOLUTION_MODE.chatMode,
                            isModeSelected = (selectedMode == ChatModeType.SOLUTION_MODE.chatMode),
                            onModeClick = { mode, _ ->
                                selectedMode = if (selectedMode == ChatModeType.SOLUTION_MODE.chatMode) null else ChatModeType.SOLUTION_MODE.chatMode
                            }
                        )
                    }

                    StrHatButton(
                        text = stringResource(R.string.select),
                        isDisabled = selectedMode == null,
                        onClick = {
                            onChatModeSelected(selectedMode)
                            onDismiss()
                            navigateToChat()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatModeBottomSheetPreview() {
    StrHatTheme {
        val (isVisible, setVisible) = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.MainWhite)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { setVisible(true) }
            ) {
                Text(
                    text = stringResource(R.string.chat_select_chat_mode_button)
                )
            }

            ChatModeBottomSheet(
                isVisible = isVisible,
                onDismiss = { setVisible(false) },
                onChatModeSelected = {},
                navigateToChat = {}
            )
        }
    }
}