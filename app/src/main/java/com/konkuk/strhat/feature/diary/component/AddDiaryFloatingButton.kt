package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun AddDiaryFloatingButton(
    onFloatingBtnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onFloatingBtnClick,
        modifier = modifier.size(60.dp),
        containerColor = colors.MainBlue,
        contentColor = colors.MainWhite,
        shape = RoundedCornerShape(30.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus_floating_button),
            contentDescription = stringResource(R.string.add_diary_floating_button_description),
            modifier = Modifier.size(24.dp)
        )
    }
}