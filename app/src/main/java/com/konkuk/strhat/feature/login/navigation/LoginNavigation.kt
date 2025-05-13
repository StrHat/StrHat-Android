package com.konkuk.strhat.feature.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.Route.Login
import com.konkuk.strhat.feature.login.LoginRoute

fun NavController.navigateToLogin(navOptions: NavOptions) {
    navigate(Login, navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    padding: PaddingValues,
    onNavigateToOnBoarding: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<Login> {
        LoginRoute(
            padding = padding,
            navigateToOnBoarding = onNavigateToOnBoarding,
            navigateToHome = onNavigateToHome
        )
    }
}