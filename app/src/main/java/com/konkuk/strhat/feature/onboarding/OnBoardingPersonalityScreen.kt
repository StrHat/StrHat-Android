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
fun OnBoardingPersonalityRoute(
    padding: PaddingValues,
    navigateToSuccess: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val personality by viewModel.personality.collectAsState()

    OnBoardingPersonalityScreen(
        padding = padding,
        personality = personality,
        onPersonalityChange = viewModel::updatePersonality,
        navigateToSuccess = navigateToSuccess
    )
}

@Composable
fun OnBoardingPersonalityScreen(
    padding: PaddingValues,
    personality: String,
    onPersonalityChange: (String) -> Unit,
    navigateToSuccess: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        OnBoardingPersonalitySection(
            personality = personality,
            onPersonalityChange = onPersonalityChange
        )

        StrHatButton(
            isDisabled = if (personality.isEmpty()) true else false,
            text = stringResource(R.string.next),
            onClick = {
                if (personality.isNotEmpty())
                    navigateToSuccess()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun OnBoardingPersonalitySection(
    personality: String,
    onPersonalityChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AnimatedProgressBar(5 / 6f)

        PageDescriptionSection(
            titleResId = R.string.onboarding_personality_title,
            descriptionResId = R.string.onboarding_type_description,
            modifier = Modifier.padding(top = 40.dp, bottom = 30.dp)
        )

        LongTextField(
            value = personality,
            onValueChange = onPersonalityChange,
            hint = stringResource(R.string.textfield_personality_type),
            maxLength = 1000,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PreviewOnBoardingPersonalityScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        OnBoardingPersonalityScreen(
            padding = PaddingValues(),
            personality = "",
            onPersonalityChange = {},
            navigateToSuccess = {}
        )
    }
}