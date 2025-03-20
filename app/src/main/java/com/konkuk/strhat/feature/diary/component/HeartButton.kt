package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun HeartButton(
    modeText: String,
    isModeSelected: Boolean,
    onModeClick: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val btnBackgroundTint = if (isModeSelected) colors.SubBlue else colors.Gray100
    val textColor = if (isModeSelected) colors.MainBlue else colors.Gray500

    Box(
        modifier = modifier
            .size(130.dp)
            .noRippleClickable {
                onModeClick(modeText, isModeSelected)
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_heart_btn),
            contentDescription = modeText,
            modifier = Modifier
                .fillMaxSize(),
            colorFilter = ColorFilter.tint(btnBackgroundTint)
        )

        Text(
            text = modeText,
            style = typography.head2_b_20,
            color = textColor,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}