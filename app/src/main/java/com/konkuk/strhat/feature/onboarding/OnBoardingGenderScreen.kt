package com.konkuk.strhat.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.button.StrHatSelectableButtons
import com.konkuk.strhat.core.component.dropdown.JobDropDown
import com.konkuk.strhat.core.component.progressbar.AnimatedProgressBar
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun OnBoardingGenderRoute(
    padding: PaddingValues,
    navigateToHobby: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val selectedOption by viewModel.selectedOption.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.updateProgress(2 / 6f)
    }

    val progress by viewModel.progress.collectAsState()

    OnBoardingGenderScreen(
        padding = padding,
        progress = progress,
        selectedOption = selectedOption,
        onOptionSelected = viewModel::updateSelectedOption,
        navigateToHobby = navigateToHobby
    )

}

@Composable
fun OnBoardingGenderScreen(
    padding: PaddingValues,
    progress: Float,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    navigateToHobby: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        OnBoardingGenderSection(
            progress = progress,
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected
        )

        StrHatButton(
            isDisabled = if (selectedOption.isEmpty()) true else false,
            text = stringResource(R.string.next),
            onClick = {
                if (selectedOption.isNotEmpty())
                    navigateToHobby()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun OnBoardingGenderSection(
    progress: Float,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = listOf(
        "남성", "여성"
    )

    Column(
        modifier = modifier
    ) {
        AnimatedProgressBar(progress)

        PageDescriptionSection(
            titleResId = R.string.onboarding_gender_title,
            descriptionResId = R.string.onboarding_description,
            modifier = Modifier.padding(top = 40.dp, bottom = 50.dp)
        )
        StrHatSelectableButtons(
            options = options,
            onOptionSelected = onOptionSelected,
            selectedOption = selectedOption
        )
        Spacer(Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.onboarding_job),
            color = colors.MainBlack,
            style = typography.title1_b_18,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        JobDropDown(onJobSelected = {})
    }
}

@Preview
@Composable
private fun PreviewOnBoardingGenderScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        OnBoardingGenderScreen(
            padding = PaddingValues(),
            progress = 0f,
            selectedOption = "",
            onOptionSelected = {},
            navigateToHobby = {}
        )
    }
}