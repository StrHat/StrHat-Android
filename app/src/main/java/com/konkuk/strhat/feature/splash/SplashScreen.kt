package com.konkuk.strhat.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun SplashRoute(
    padding: PaddingValues,
    navigateToLogin: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val navigateToLoginState by viewModel.navigateToLogin.collectAsState()

    LaunchedEffect(navigateToLoginState) {
        if (navigateToLoginState) {
            navigateToLogin()
        }
    }
    SplashScreen(padding = padding)
}

@Composable
fun SplashScreen(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .background(color = colors.MainBlue)
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_logo_white),
            contentDescription = stringResource(R.string.app_name_korean),
            tint = colors.MainWhite
        )
    }
}

@Preview
@Composable
private fun PreviewSplashScreen() {
    StrHatTheme {
        SplashScreen(
            padding = PaddingValues()
        )
    }
}