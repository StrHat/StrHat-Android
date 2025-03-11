package com.konkuk.strhat.core.component.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun PageDescriptionSection(
    titleResId: Int,
    modifier: Modifier = Modifier,
    descriptionResId: Int? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(titleResId),
            color = colors.MainBlack,
            style = typography.head1_b_24
        )

        if (descriptionResId != null) {
            Text(
                text = stringResource(descriptionResId),
                color = colors.MainBlack,
                style = typography.body3_r_14,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewPageDescriptionSection1() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colors.MainWhite)
    ) {
        PageDescriptionSection(
            titleResId = R.string.onboarding_title,
            descriptionResId = R.string.onboarding_description
        )
    }
}

@Preview
@Composable
private fun PreviewPageDescriptionSection2() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colors.MainWhite)
    ) {
        PageDescriptionSection(
            titleResId = R.string.onboarding_title
        )
    }
}