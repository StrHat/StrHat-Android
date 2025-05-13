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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.dialog.StrHatDialog
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.domain.entity.MyPageModel
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import java.util.Calendar

@Composable
fun MyPageRoute(
    padding: PaddingValues,
    navigateToAccount: () -> Unit,
    navigateToHealing: () -> Unit,
    navigateToStress: () -> Unit,
    navigateToPersonality: () -> Unit,
    navigateToMySelfDiagnosisRecord: () -> Unit,
    navigateToTodayStressScore: () -> Unit,
    navigateToChangeGraph: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMyPageModel()
    }
    val myPageModel by viewModel.myPageModel.collectAsState()

    MyPageScreen(
        padding = padding,
        navigateToAccount = navigateToAccount,
        navigateToHealing = navigateToHealing,
        navigateToStress = navigateToStress,
        navigateToPersonality = navigateToPersonality,
        navigateToMySelfDiagnosisRecord = navigateToMySelfDiagnosisRecord,
        navigateToTodayStressScore = navigateToTodayStressScore,
        navigateToChangeGraph = navigateToChangeGraph,
        onSignOutClick = { viewModel.signOut() },
        myPageModel = myPageModel
    )
}

@Composable
private fun MyPageScreen(
    padding: PaddingValues,
    navigateToAccount: () -> Unit,
    navigateToHealing: () -> Unit,
    navigateToStress: () -> Unit,
    navigateToPersonality: () -> Unit,
    navigateToMySelfDiagnosisRecord: () -> Unit,
    navigateToTodayStressScore: () -> Unit,
    navigateToChangeGraph: () -> Unit,
    onSignOutClick: () -> Unit,
    myPageModel: MyPageModel
) {
    var isLogoutDialogVisible by remember { mutableStateOf(false) }
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val age = currentYear - myPageModel.birth + 1

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding)
    ) {
        PageDescriptionSection(titleResId = R.string.my_title)

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
                        text = age.toString(),
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

                Text(
                    text = stringResource(R.string.my_modify),
                    style = typography.body4_r_12,
                    color = colors.Gray400,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .noRippleClickable(navigateToAccount)
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

                Text(
                    text = stringResource(R.string.my_modify),
                    style = typography.body4_r_12,
                    color = colors.Gray400,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .noRippleClickable(navigateToHealing)
                )
            }

            Text(
                text = myPageModel.hobbyHealingStyle,
                style = typography.body2_r_15,
                color = colors.Gray500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
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

                Text(
                    text = stringResource(R.string.my_modify),
                    style = typography.body4_r_12,
                    color = colors.Gray400,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .noRippleClickable(navigateToStress)
                )
            }

            Text(
                text = myPageModel.stressReliefStyle,
                style = typography.body2_r_15,
                color = colors.Gray500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
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

                Text(
                    text = stringResource(R.string.my_modify),
                    style = typography.body4_r_12,
                    color = colors.Gray400,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .noRippleClickable(navigateToPersonality)
                )
            }

            Text(
                text = myPageModel.personality,
                style = typography.body2_r_15,
                color = colors.Gray500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
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
            modifier = Modifier
                .padding(bottom = 15.dp)
                .noRippleClickable {
                    navigateToChangeGraph()
                }
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
            modifier = Modifier
                .padding(bottom = 15.dp)
                .noRippleClickable {
                    navigateToTodayStressScore()
                }
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
            modifier = Modifier
                .padding(bottom = 15.dp)
                .noRippleClickable {
                    navigateToMySelfDiagnosisRecord()
                }
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
            modifier = Modifier
                .padding(bottom = 15.dp)
                .noRippleClickable { isLogoutDialogVisible = true }
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = colors.Gray300,
            modifier = Modifier.padding(bottom = 15.dp)
        )
    }

    if (isLogoutDialogVisible) {
        Dialog(onDismissRequest = { isLogoutDialogVisible = false }) {
            StrHatDialog(
                titleResId = R.string.dialog_logout_title,
                imageResId = R.drawable.ic_strhat_dialog_all,
                imageRatio = 2f / 1f,
                descriptionResId = R.string.dialog_logout_description,
                onConfirmButtonClick = {
                    isLogoutDialogVisible = false
                    onSignOutClick()
                },
                onDismissButtonClick = { isLogoutDialogVisible = false }
            )
        }
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
            navigateToAccount = {},
            navigateToHealing = {},
            navigateToStress = {},
            navigateToPersonality = {},
            navigateToMySelfDiagnosisRecord = {},
            navigateToTodayStressScore = {},
            navigateToChangeGraph = {},
            onSignOutClick = {},
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