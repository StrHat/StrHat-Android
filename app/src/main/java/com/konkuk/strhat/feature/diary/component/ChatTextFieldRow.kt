package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun ChatTextFieldRow(
    message: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.Gray200,
                shape = RoundedCornerShape(size = 8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = message,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = "메시지를 입력하세요",
                    style = typography.body3_b_14,
                    color = colors.Gray400
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = colors.Gray600,
                unfocusedTextColor = colors.Gray600
            ),
            modifier = Modifier
                .weight(1f)
                .background(
                    color = colors.Gray200,
                    shape = RoundedCornerShape(24.dp)
                ),
            maxLines = 5,
            textStyle = typography.body3_b_14
        )

        Image(
            painter = painterResource(R.drawable.ic_send),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 20.dp)
                .noRippleClickable { onSendClick() }
        )
    }
}

@Preview
@Composable
private fun ChatTextFieldRowPreview() {
    StrHatTheme {
        ChatTextFieldRow(
            message = "",
            onTextChange = {},
            onSendClick = {}
        )
    }
}