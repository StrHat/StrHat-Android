package com.konkuk.strhat.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.datetime.LocalDate

@Composable
fun DiarySummaryView(
    date: LocalDate,
    content: String,
    onSummaryViewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .noRippleClickable(onSummaryViewClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(
                    id = R.string.diary_summary_title,
                    date.year,
                    date.monthNumber,
                    date.dayOfMonth
                ),
                style = typography.title1_b_18,
                color = colors.MainBlack
            )

            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                tint = colors.Gray300,
                contentDescription = stringResource(R.string.diary_summary_view_arrow_description),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_strhat_blue_shadow),
                contentDescription = stringResource(R.string.no_diary_empty_view_strhat_description),
                modifier = Modifier
                    .width(80.dp)
            )
            
            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = content,
                style = typography.body4_r_12,
                color = colors.MainBlack,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun DiarySummaryViewPreview(
    modifier: Modifier = Modifier
) {
    StrHatTheme {
        DiarySummaryView(
            date = LocalDate(2025, 3, 11),
            content = "일기 내용 요약입니다 일기 내용 요약입니다 일기 내용 요약입니다 일기 내용 요약입니다 일기 내용 요약입니다 일기 내용 요약입니다 일기 내용 요약입니다",
            onSummaryViewClick = {},
            modifier = modifier.background(colors.MainWhite)
        )
    }
}