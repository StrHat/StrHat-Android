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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun OnBoardingRoute(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    OnBoardingScreen(
        padding = padding,
        onButtonClick = {
            navigateToHome()
        }
    )
}

@Composable
fun OnBoardingScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
) {
    var nickname by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        OnBoardingNickNameSection(
            nickname = nickname,
            onNickNameChange = { nickname = it },
            selectedYear = selectedYear,
            onYearSelected = { selectedYear = it },
            modifier = Modifier.align(alignment = Alignment.TopStart)
        )

        StrHatButton(
            isDisabled = if (nickname.isEmpty() || selectedYear == 0) true else false,
            text = stringResource(R.string.next),
            onClick = onButtonClick,
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
        AnimatedProgressBar(progress = 0f)

        PageDescriptionSection(
            titleResId = R.string.onboarding_title,
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
        OnBoardingScreen(
            padding = PaddingValues(),
            onButtonClick = {}
        )
    }
}