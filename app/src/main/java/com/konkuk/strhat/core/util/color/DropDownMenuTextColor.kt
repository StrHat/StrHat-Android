package com.konkuk.strhat.core.util.color

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.konkuk.strhat.ui.theme.Gray500
import com.konkuk.strhat.ui.theme.MainBlack
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun setDropDownMenuTextStyle(selectedFilter: String, filter: String): DropDownMenuTextStyle {
    val typography =
        if (selectedFilter == filter) {
            typography.body1_m_16
        } else {
            typography.body1_r_16
        }

    val color = if (selectedFilter == filter) MainBlack else Gray500
    return DropDownMenuTextStyle(color = color, typography = typography)
}

data class DropDownMenuTextStyle(
    val color: Color,
    val typography: TextStyle
)