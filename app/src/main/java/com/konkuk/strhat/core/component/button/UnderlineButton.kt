package com.konkuk.strhat.core.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun UnderlineButton(
    btnText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = btnText,
            style = typography.body4_m_12,
            color = colors.Gray500,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        HorizontalDivider(
            modifier = Modifier.width(70.dp),
            thickness = 1.dp,
            color = colors.Gray500
        )
    }
}

@Preview
@Composable
private fun UnderlineButtonPreview() {
    StrHatTheme {
        UnderlineButton(
            btnText = stringResource(R.string.diary_ai_total_diary_button)
        )
    }
}