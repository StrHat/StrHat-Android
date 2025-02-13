package com.konkuk.strhat.feature.login

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
fun LoginRoute(
    padding: PaddingValues,
    navigateToOnBoarding: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        padding = padding,
        onButtonClick = {
            navigateToOnBoarding()
        }
    )
}

@Composable
fun LoginScreen(
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
            text = "Login Screen"
        )
        Button(
            onClick = onButtonClick,
        ) {
            Text(
                text = "Go To OnBoarding"
            )
        }
    }
}