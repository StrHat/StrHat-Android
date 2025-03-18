package com.konkuk.strhat.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
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
    LaunchedEffect(Unit) {
        viewModel.updateHomeModel()
    }
    val homeModel by viewModel.homeModel.collectAsState()

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
    val textStyle = typography.head2_b_20
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding)
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

        if (homeModel.hasDiary) {
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
                        style = SpanStyle(
                            color = colors.MainBlue,
                            fontSize = textStyle.fontSize,
                            fontWeight = textStyle.fontWeight,
                            fontFamily = textStyle.fontFamily,
                            letterSpacing = textStyle.letterSpacing
                        ),
                        start = 9,
                        end = 13 + homeModel.positiveEmotions[0].length + homeModel.positiveEmotions[1].length + homeModel.positiveEmotions[2].length
                    )
                },
                style = typography.head2_r_20,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
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
                    contentDescription = stringResource(R.string.home_image_description),
                    modifier = Modifier.size(
                        width = LocalConfiguration.current.screenWidthDp.dp * 0.36f,
                        height = LocalConfiguration.current.screenHeightDp.dp * 0.14f
                    )
                )

                Box(
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .size(
                            width = LocalConfiguration.current.screenWidthDp.dp * 0.4f,
                            height = LocalConfiguration.current.screenHeightDp.dp * 0.31f
                        )
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
                            else colors.SubRed,
                            fontSize = textStyle.fontSize,
                            fontWeight = textStyle.fontWeight,
                            fontFamily = textStyle.fontFamily,
                            letterSpacing = textStyle.letterSpacing
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
        } else {
            Text(
                text = stringResource(R.string.home_empty_sub_title),
                style = typography.head2_r_20,
                color = colors.MainBlack
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_strhat_empty_blue_yellow),
                    contentDescription = stringResource(R.string.home_empty_image_description),
                    modifier = Modifier.size(
                        width = LocalConfiguration.current.screenWidthDp.dp * 0.4f,
                        height = LocalConfiguration.current.screenHeightDp.dp * 0.15f
                    )
                )

                Text(
                    text = stringResource(R.string.home_empty_description),
                    textAlign = TextAlign.Center,
                    style = typography.body2_r_15,
                    color = colors.MainBlack
                )

                Text(
                    text = stringResource(R.string.home_empty_sub_description),
                    style = typography.body2_r_15,
                    color = colors.Gray400
                )
            }
        }
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
                hasDiary = true,
                nickname = "송밍서",
                positiveEmotions = listOf("밍서", "융서", "밍서"),
                emotion = 4,
                stressReliefSuggestion = "시험 스트레스를 조금이나마 덜어줄 수 있는 방법으로, 독서와 음악을 조합해보는 건 어떨까요? 좋아하는 음악을 들으며 독서를 즐기면서 마음을 편하게 해보세요.",
                stressScore = 10,
                stressLevel = "높은 스트레스 수준"
            )
        )
    }
}