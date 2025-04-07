package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.UnderlineButton
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun ChatTopBar(
    modifier: Modifier = Modifier,
    showQuitBtn: Boolean = true,
    onChatQuitBtnClick: () -> Unit = {}
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(colors.MainWhite)
        ) {
            Text(
                text = stringResource(R.string.chat_top_bar_title),
                style = typography.body1_b_16,
                color = colors.MainBlack,
                modifier = Modifier.align(Alignment.Center),
            )
            if (showQuitBtn) {
                UnderlineButton(
                    btnText = stringResource(R.string.chat_top_bar_quit_button),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(top = 4.dp, end = 20.dp)
                        .noRippleClickable {
                            onChatQuitBtnClick()
                        }
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Gray300
        )
    }
}

@Preview
@Composable
private fun ChatTopBarPreview() {
    ChatTopBar(
        onChatQuitBtnClick = {}
    )
}