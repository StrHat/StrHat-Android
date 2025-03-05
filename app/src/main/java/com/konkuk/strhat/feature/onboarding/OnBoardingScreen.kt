package com.konkuk.strhat.feature.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.core.component.progressbar.AnimatedProgressBar

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
    var progressBarValue by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {progressBarValue += 1/6f}
        ) { }

        AnimatedProgressBar(progressBarValue)

        Button(
            onClick = onButtonClick,
        ) {
            Text(
                text = "Go To Home"
            )
        }
    }
}