package com.konkuk.strhat.feature.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.Route.Login
import com.konkuk.strhat.feature.login.LoginRoute

fun NavController.navigateToLogin() {
    navigate(Login)
}

fun NavGraphBuilder.loginNavGraph(
    padding: PaddingValues,
    onNavigateToOnBoarding: () -> Unit
) {
    composable<Login> {
        LoginRoute(
            padding = padding,
            navigateToOnBoarding = onNavigateToOnBoarding
        )
    }
}