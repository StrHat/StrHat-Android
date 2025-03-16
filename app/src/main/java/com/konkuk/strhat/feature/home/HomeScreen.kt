package com.konkuk.strhat.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.domain.entity.HomeModel
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun HomeRoute(
    padding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeModel by viewModel.homeModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.updateHomeModel(homeModel)
    }

    HomeScreen(
        padding = padding,
        homeModel = homeModel
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    homeModel: HomeModel
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.home_title, homeModel.nickname))

                addStyle(
                    style = SpanStyle(color = colors.MainBlue),
                    start = 7,
                    end = 7 + homeModel.nickname.length
                )
            },
            style = typography.head1_b_24,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = buildAnnotatedString {
                append(
                    stringResource(
                        R.string.home_keyword,
                        homeModel.positiveEmotions[0],
                        homeModel.positiveEmotions[1],
                        homeModel.positiveEmotions[2]
                    )
                )

                addStyle(
                    style = SpanStyle(color = colors.MainBlue),
                    start = 9,
                    end = 13 + homeModel.positiveEmotions[0].length + homeModel.positiveEmotions[1].length + homeModel.positiveEmotions[2].length
                )
            },
            style = typography.head2_r_20,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Row(
            modifier = Modifier.padding(bottom = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    when (homeModel.emotion) {
                        1 -> R.drawable.ic_strhat_blue_shadow
                        2 -> R.drawable.ic_strhat_red_shadow
                        3 -> R.drawable.ic_strhat_gray_shadow
                        4 -> R.drawable.ic_strhat_yellow_shadow
                        5 -> R.drawable.ic_strhat_green_shadow
                        else -> R.drawable.ic_strhat_blue_shadow
                    }
                ),
                contentDescription = stringResource(R.string.home_image_description)
            )

            Box(
                modifier = Modifier
                    .padding(start = 25.dp)
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.31f)
                    .background(color = colors.Gray100, shape = RoundedCornerShape(12.dp))
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = homeModel.stressReliefSuggestion,
                    color = colors.Gray600,
                    style = typography.body3_b_14
                )
            }
        }

        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.home_stress_title, homeModel.stressScore))

                addStyle(
                    style = SpanStyle(color = colors.MainBlue),
                    start = 13,
                    end = 13 + homeModel.stressScore.toString().length
                )
            },
            style = typography.head1_b_24,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.home_stress_sub_title, homeModel.stressLevel))

                addStyle(
                    style = SpanStyle(
                        color =
                        if (homeModel.stressScore < 6) colors.MainBlue
                        else if (homeModel.stressScore < 9) colors.MainRed
                        else colors.SubRed
                    ),
                    start = 0,
                    end = 10
                )
            },
            style = typography.head2_r_20,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Text(
            text = stringResource(R.string.home_stress_description),
            color = colors.MainBlack,
            style = typography.body2_r_15
        )
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        HomeScreen(
            padding = PaddingValues(),
            homeModel = HomeModel(
                nickname = "송밍서",
                positiveEmotions = listOf("밍서", "융서", "밍서"),
                emotion = 4,
                stressReliefSuggestion = "스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스스트레스",
                stressScore = 10,
                stressLevel = "높은 스트레스 수준"
            )
        )
    }
}