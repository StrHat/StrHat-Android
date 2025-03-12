package com.konkuk.strhat.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.progressbar.AnimatedProgressBar
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.component.textfield.LongTextField
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun OnBoardingHobbyRoute(
    padding: PaddingValues,
    navigateToStress: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val hobby by viewModel.hobbyText.collectAsState()

    OnBoardingHobbyScreen(
        padding = padding,
        hobby = hobby,
        onHobbyChange = viewModel::updateHobby,
        navigateToStress = navigateToStress
    )
}

@Composable
fun OnBoardingHobbyScreen(
    padding: PaddingValues,
    hobby: String,
    onHobbyChange: (String) -> Unit,
    navigateToStress: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        OnBoardingHobbySection(
            hobby = hobby,
            onHobbyChange = onHobbyChange
        )

        StrHatButton(
            isDisabled = if (hobby.isEmpty()) true else false,
            text = stringResource(R.string.next),
            onClick = {
                if (hobby.isNotEmpty())
                    navigateToStress()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun OnBoardingHobbySection(
    hobby: String,
    onHobbyChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AnimatedProgressBar(3 / 6f)

        PageDescriptionSection(
            titleResId = R.string.onboarding_hobby_title,
            descriptionResId = R.string.onboarding_type_description,
            modifier = Modifier.padding(top = 40.dp, bottom = 30.dp)
        )

        LongTextField(
            value = hobby,
            onValueChange = onHobbyChange,
            hint = stringResource(R.string.textfield_hobby),
            maxLength = 1000,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PreviewOnBoardingHobbyScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        OnBoardingHobbyScreen(
            padding = PaddingValues(),
            hobby = "",
            onHobbyChange = {},
            navigateToStress = {}
        )
    }
}