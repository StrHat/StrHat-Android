package com.konkuk.strhat.feature.splash.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.Route.Splash
import com.konkuk.strhat.feature.splash.SplashRoute

fun NavGraphBuilder.splashNavGraph(
    padding: PaddingValues,
    onNavigateToLogin: () -> Unit
) {
    composable<Splash> {
        SplashRoute(
            padding = padding,
            navigateToLogin = onNavigateToLogin
        )
    }
}