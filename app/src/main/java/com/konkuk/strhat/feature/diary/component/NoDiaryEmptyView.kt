package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun NoDiaryEmptyView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.no_diary_empty_view_title),
            style = typography.body1_r_16,
            color = colors.MainBlack
        )

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(R.drawable.ic_strhat_gray_shadow),
            contentDescription = stringResource(R.string.no_diary_empty_view_strhat_description),
            modifier = Modifier
                .align(CenterHorizontally)
                .width(80.dp)
        )
    }
}

@Preview
@Composable
fun NoDiaryEmptyViewPreview(
    modifier: Modifier = Modifier
) {
    StrHatTheme {
        NoDiaryEmptyView(
            modifier = modifier.background(colors.MainWhite)
        )
    }
}