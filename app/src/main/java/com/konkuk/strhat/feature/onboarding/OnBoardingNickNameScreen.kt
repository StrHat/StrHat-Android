package com.konkuk.strhat.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.konkuk.strhat.core.component.progressbar.AnimatedProgressBar
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.component.textfield.ShortTextField
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun OnBoardingNickNameRoute(
    padding: PaddingValues,
    navigateToGender: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val nickName by viewModel.nickName.collectAsState()
    val selectedYear by viewModel.selectedYear.collectAsState()

    OnBoardingNickNameScreen(
        padding = padding,
        nickName = nickName,
        onNickNameChange = viewModel::updateNickName,
        selectedYear = selectedYear,
        onYearSelected = viewModel::updateSelectedYear,
        navigateToGender = navigateToGender
    )
}

@Composable
fun OnBoardingNickNameScreen(
    padding: PaddingValues,
    nickName: String,
    onNickNameChange: (String) -> Unit,
    selectedYear: Int,
    onYearSelected: (Int) -> Unit,
    navigateToGender: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        OnBoardingNickNameSection(
            nickname = nickName,
            onNickNameChange = onNickNameChange,
            selectedYear = selectedYear,
            onYearSelected = onYearSelected,
            modifier = Modifier.align(alignment = Alignment.TopStart)
        )

        StrHatButton(
            isDisabled = if (nickName.isEmpty() || selectedYear == 0) true else false,
            text = stringResource(R.string.next),
            onClick = {
                if (nickName.isNotEmpty() && selectedYear != 0)
                    navigateToGender()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun OnBoardingNickNameSection(
    nickname: String,
    onNickNameChange: (String) -> Unit,
    selectedYear: Int,
    onYearSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AnimatedProgressBar(1 / 6f)

        PageDescriptionSection(
            titleResId = R.string.onboarding_nickname_title,
            descriptionResId = R.string.onboarding_description,
            modifier = Modifier.padding(top = 40.dp, bottom = 50.dp)
        )

        Text(
            text = stringResource(R.string.onboarding_nickname),
            color = colors.MainBlack,
            style = typography.title1_b_18,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        ShortTextField(
            value = nickname,
            onValueChange = onNickNameChange,
            hint = stringResource(R.string.textfield_nickname),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        )

        YearSelectableButton(
            selectedYear = selectedYear,
            onYearSelected = onYearSelected
        )
    }
}

@Preview
@Composable
private fun PreviewOnBoardingScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        OnBoardingNickNameScreen(
            padding = PaddingValues(),
            nickName = "",
            onNickNameChange = {},
            selectedYear = 0,
            onYearSelected = {},
            navigateToGender = {}
        )
    }
}