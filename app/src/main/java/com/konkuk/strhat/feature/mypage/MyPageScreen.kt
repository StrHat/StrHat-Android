package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.domain.entity.MyPageModel
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun MyPageRoute(
    padding: PaddingValues,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.updateMyPageModel()
    }
    val myPageModel by viewModel.myPageModel.collectAsState()

    MyPageScreen(
        padding = padding, myPageModel = myPageModel
    )
}

@Composable
private fun MyPageScreen(
    padding: PaddingValues,
    myPageModel: MyPageModel
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding)
    ) {
        Text(
            text = stringResource(R.string.my_title),
            style = typography.head1_b_24,
            color = colors.MainBlack
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .border(
                        width = 3.dp, color = colors.MainBlue, shape = RoundedCornerShape(12.dp)
                    )
                    .padding(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.my_nickname),
                        style = typography.head2_r_20,
                        color = colors.MainBlack,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(
                        text = myPageModel.nickname,
                        style = typography.head2_r_20,
                        color = colors.Gray400
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.my_age),
                        style = typography.head2_r_20,
                        color = colors.MainBlack,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(
                        text = myPageModel.birth.toString(),
                        style = typography.head2_r_20,
                        color = colors.Gray400
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.my_gender),
                        style = typography.head2_r_20,
                        color = colors.MainBlack,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(
                        text = myPageModel.gender,
                        style = typography.head2_r_20,
                        color = colors.Gray400
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.my_job),
                        style = typography.head2_r_20,
                        color = colors.MainBlack,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(
                        text = myPageModel.job,
                        style = typography.head2_r_20,
                        color = colors.Gray400
                    )
                }

                Text(text = stringResource(R.string.my_modify),
                    style = typography.body4_r_12,
                    color = colors.Gray400,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .noRippleClickable { }
                )
            }

            Image(
                painter = painterResource(R.drawable.ic_strhat_yellow_shadow),
                contentDescription = stringResource(R.string.my_image_description),
                modifier = Modifier.size(140.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 25.dp, bottom = 20.dp)
                .background(color = colors.SubBlue, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.my_healing_title),
                    style = typography.title1_b_18,
                    color = colors.MainBlack
                )

                Text(text = stringResource(R.string.my_modify),
                    style = typography.body4_r_12,
                    color = colors.Gray400,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .noRippleClickable { }
                )
            }

            Text(
                text = myPageModel.hobbyHealingStyle,
                style = typography.body2_r_15,
                color = colors.Gray500
            )
        }

        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .background(color = colors.Gray100, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.my_stress_title),
                    style = typography.title1_b_18,
                    color = colors.MainBlack
                )

                Text(text = stringResource(R.string.my_modify),
                    style = typography.body4_r_12,
                    color = colors.Gray400,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .noRippleClickable { }
                )
            }

            Text(
                text = myPageModel.stressReliefStyle,
                style = typography.body2_r_15,
                color = colors.Gray500
            )
        }

        Column(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .background(color = colors.SubBlue, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.my_personality_title),
                    style = typography.title1_b_18,
                    color = colors.MainBlack
                )

                Text(text = stringResource(R.string.my_modify),
                    style = typography.body4_r_12,
                    color = colors.Gray400,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .noRippleClickable { }
                )
            }

            Text(
                text = myPageModel.personality,
                style = typography.body2_r_15,
                color = colors.Gray500
            )
        }

        Text(
            text = stringResource(R.string.my_more_title),
            style = typography.head1_b_24,
            color = colors.MainBlack,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            text = stringResource(R.string.my_more_graph),
            style = typography.body1_r_16,
            color = colors.MainBlack,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Gray300,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        Text(
            text = stringResource(R.string.my_more_stress_score),
            style = typography.body1_r_16,
            color = colors.MainBlack,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Gray300,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        Text(
            text = stringResource(R.string.my_more_record),
            style = typography.body1_r_16,
            color = colors.MainBlack,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Gray300,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        Text(
            text = stringResource(R.string.my_more_logout),
            style = typography.body1_r_16,
            color = colors.MainBlack,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Gray300,
            modifier = Modifier.padding(bottom = 15.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewMyPageScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        MyPageScreen(
            padding = PaddingValues(),
            myPageModel = MyPageModel(
                nickname = "송밍서",
                birth = 2001,
                gender = "MALE",
                job = "STUDENT",
                hobbyHealingStyle = "1. 혼자만의 시간을 보내며 독서를 좋아함. ...",
                stressReliefStyle = "1. 집 앞 공원에 나가 찬 공기를 ...",
                personality = "1. 내성적인 편임 ..."
            )
        )
    }
}