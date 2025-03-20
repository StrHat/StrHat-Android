package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.bottomsheet.YearSelectableButton
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.button.StrHatSelectableButtons
import com.konkuk.strhat.core.component.dropdown.JobDropDown
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.component.textfield.ShortTextField
import com.konkuk.strhat.domain.entity.MyPageModel
import com.konkuk.strhat.domain.type.GenderType
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun MyAccountRoute(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMyPageModel()
    }
    val myPageModel by viewModel.myPageModel.collectAsState()

    MyAccountScreen(
        padding = padding,
        myPageModel = myPageModel,
        onNickNameChange = viewModel::updateNickName,
        onYearSelected = viewModel::updateBirth,
        onOptionSelected = viewModel::updateGender,
        navigateToMyPage = navigateToMyPage
    )
}

@Composable
private fun MyAccountScreen(
    padding: PaddingValues,
    myPageModel: MyPageModel,
    onNickNameChange: (String) -> Unit,
    onYearSelected: (Int) -> Unit,
    onOptionSelected: (String) -> Unit,
    navigateToMyPage: () -> Unit,
) {
    val options = listOf(
        GenderType.MALE.type,
        GenderType.FEMALE.type
    )

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        Column {
            PageDescriptionSection(
                titleResId = R.string.my_account_title,
                descriptionResId = R.string.onboarding_description
            )
            Spacer(Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.onboarding_nickname),
                color = colors.MainBlack,
                style = typography.title1_b_18,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            ShortTextField(
                value = myPageModel.nickname,
                onValueChange = onNickNameChange,
                hint = stringResource(R.string.textfield_nickname),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )

            YearSelectableButton(
                selectedYear = myPageModel.birth,
                onYearSelected = onYearSelected
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.my_gender_title),
                style = typography.title1_b_18,
                color = colors.MainBlack,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            StrHatSelectableButtons(
                options = options,
                onOptionSelected = onOptionSelected,
                selectedOption = myPageModel.gender
            )
            Spacer(Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.onboarding_job),
                color = colors.MainBlack,
                style = typography.title1_b_18,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            JobDropDown(onJobSelected = {})

        }

        StrHatButton(
            isDisabled = false,
            text = stringResource(R.string.confirm),
            onClick = navigateToMyPage,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@Preview
@Composable
private fun PreviewMyAccountScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        MyAccountScreen(
            padding = PaddingValues(),
            myPageModel = MyPageModel(
                nickname = "송밍서",
                birth = 2001,
                gender = "MALE",
                job = "STUDENT",
                hobbyHealingStyle = "1. 혼자만의 시간을 보내며 독서를 좋아함. ...",
                stressReliefStyle = "1. 집 앞 공원에 나가 찬 공기를 ...",
                personality = "1. 내성적인 편임 ..."
            ),
            onNickNameChange = {},
            onYearSelected = {},
            onOptionSelected = {},
            navigateToMyPage = {}
        )
    }
}