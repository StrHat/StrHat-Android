package com.konkuk.strhat.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun StrHatButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDisabled: Boolean = false,
    font: TextStyle = typography.body1_m_16,
) {
    val backgroundColor: Color
    val textColor: Color

    when (isDisabled) {
        true -> {
            backgroundColor = colors.Gray300
            textColor = colors.MainWhite
        }

        else -> {
            backgroundColor = colors.MainBlue
            textColor = colors.MainWhite
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(backgroundColor, shape = RoundedCornerShape(size = 4.dp))
            .noRippleClickable(onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = textColor,
                textAlign = TextAlign.Center,
                style = font
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LargeButtonPreview(
    modifier: Modifier = Modifier
) {
    StrHatTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .background(colors.MainWhite)
                .padding(horizontal = 20.dp)
        ) {
            StrHatButton(
                isDisabled = true,
                text = stringResource(R.string.dismiss),
                modifier = Modifier.weight(1f),
                onClick = {}
            )
            StrHatButton(
                isDisabled = false,
                text = stringResource(R.string.confirm),
                modifier = Modifier.weight(1f),
                onClick = {}
            )
        }
    }
}