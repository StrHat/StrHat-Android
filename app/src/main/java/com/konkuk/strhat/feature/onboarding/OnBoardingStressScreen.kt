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
fun OnBoardingStressRoute(
    padding: PaddingValues,
    navigateToPersonality: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val stress by viewModel.stress.collectAsState()

    OnBoardingStressScreen(
        padding = padding,
        stress = stress,
        onStressChange = viewModel::updateStress,
        navigateToPersonality = navigateToPersonality
    )
}

@Composable
fun OnBoardingStressScreen(
    padding: PaddingValues,
    stress: String,
    onStressChange: (String) -> Unit,
    navigateToPersonality: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        OnBoardingStressSection(
            stress = stress,
            onStressChange = onStressChange
        )

        StrHatButton(
            isDisabled = if (stress.isEmpty()) true else false,
            text = stringResource(R.string.next),
            onClick = {
                if (stress.isNotEmpty())
                    navigateToPersonality()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun OnBoardingStressSection(
    stress: String,
    onStressChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AnimatedProgressBar(4 / 6f)

        PageDescriptionSection(
            titleResId = R.string.onboarding_stress_title,
            descriptionResId = R.string.onboarding_type_description,
            modifier = Modifier.padding(top = 40.dp, bottom = 30.dp)
        )

        LongTextField(
            value = stress,
            onValueChange = onStressChange,
            hint = stringResource(R.string.textfield_stress_management),
            maxLength = 1000,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PreviewOnBoardingStressScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        OnBoardingStressScreen(
            padding = PaddingValues(),
            stress = "",
            onStressChange = {},
            navigateToPersonality = {}
        )
    }
}