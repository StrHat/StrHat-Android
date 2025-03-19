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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.core.component.bottomsheet.draghandle.BottomSheetDragHandle
import com.konkuk.strhat.core.component.button.StrHatButton
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
                        text = "스틀햇 모드 선택",
                        style = typography.title1_b_18,
                        color = colors.MainBlack
                    )
                    Text(
                        text = "스틀햇과 나누고 싶은 대화 모드를 선택해주세요!",
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
                            modeText = "공감",
                            isModeSelected = (selectedMode == "공감"),
                            onModeClick = { modeText, _ ->
                                selectedMode = if (selectedMode == "공감") null else "공감"
                            }
                        )

                        Spacer(modifier = Modifier.width(50.dp))

                        HeartButton(
                            modeText = "해결책",
                            isModeSelected = (selectedMode == "해결책"),
                            onModeClick = { modeText, _ ->
                                selectedMode = if (selectedMode == "해결책") null else "해결책"
                            }
                        )
                    }

                    StrHatButton(
                        text = "선택",
                        isDisabled = selectedMode == null,
                        onClick = {
                            onChatModeSelected(selectedMode)
                            onDismiss()
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
                    text = "대화 모드 선택 버튼"
                )
            }

            ChatModeBottomSheet(
                isVisible = isVisible,
                onDismiss = { setVisible(false) },
                onChatModeSelected = {}
            )
        }
    }
}