package com.konkuk.strhat.feature.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashRoute(
    padding: PaddingValues,
    navigateToLogin: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    SplashScreen(
        padding = padding,
        onButtonClick = {
            navigateToLogin()
        }
    )
}

@Composable
fun SplashScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Splash Screen"
        )
        Button(
            onClick = onButtonClick,
        ) {
            Text(
                text = "Go To Login"
            )
        }
    }
}