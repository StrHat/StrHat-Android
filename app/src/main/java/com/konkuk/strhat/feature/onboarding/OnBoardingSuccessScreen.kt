package com.konkuk.strhat.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.progressbar.AnimatedProgressBar
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun OnBoardingSuccessRoute(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    viewModel: OnBoardingViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.updateProgress(6 / 6f)
    }

    val progress by viewModel.progress.collectAsStateWithLifecycle()

    OnBoardingSuccessScreen(
        padding = padding,
        progress = progress,
        onClick = {
            viewModel.requestSignUp(
                onSuccess = navigateToHome
            )
        }
    )
}

@Composable
fun OnBoardingSuccessScreen(
    padding: PaddingValues,
    progress: Float,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        OnBoardingSuccessSection(progress = progress)

        StrHatButton(
            isDisabled = false,
            text = stringResource(R.string.onboarding_success_button),
            onClick = onClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun OnBoardingSuccessSection(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Column {
        AnimatedProgressBar(progress)

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.onboarding_success_title),
                color = colors.MainBlack,
                style = typography.head1_b_24,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(80.dp))

            Icon(
                painter = painterResource(R.drawable.ic_logo_blue),
                contentDescription = stringResource(R.string.app_name_korean),
                tint = colors.MainBlue
            )
            Spacer(Modifier.height(200.dp))

        }
    }
}

@Preview
@Composable
private fun PreviewOnBoardingSuccessScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        OnBoardingSuccessScreen(
            padding = PaddingValues(),
            progress = 0f,
            onClick = {}
        )
    }
}