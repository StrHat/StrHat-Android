package com.konkuk.strhat.feature.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun TodayStressScoreEmptyView(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_strhat_gray_shadow),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "해당 날짜에는\n스트레스 점수가 존재하지 않습니다.\n\n오늘의 일기를 작성하고 확인하러 와주세요!",
                style = typography.body1_m_16,
                color = colors.Gray500,
                textAlign = TextAlign.Center
            )
        }

        StrHatButton(
            text = stringResource(R.string.my_page_go_back_button),
            onClick = {
                popBackStack()
            },
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Preview
@Composable
private fun TodayStressScoreEmptyViewPreview() {
    StrHatTheme {
        TodayStressScoreEmptyView(
            popBackStack = {}
        )
    }
}