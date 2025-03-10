package com.konkuk.strhat.feature.onboarding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OnBoardingGenderRoute(
    padding: PaddingValues,
    navigateToHobby: () -> Unit,
    modifier: Modifier = Modifier
) {
    OnBoardingGenderScreen(
        padding = padding,
    )

}

@Composable
fun OnBoardingGenderScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
private fun PreviewOnBoardingGenderScreen() {

}