package com.konkuk.strhat.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.konkuk.strhat.feature.onboarding.OnBoardingRoute
import com.konkuk.strhat.core.navigation.Route.OnBoarding

fun NavController.navigateToOnBoarding() {
    navigate(OnBoarding)
}

fun NavGraphBuilder.onBoardingNavGraph(
    padding: PaddingValues,
    onNavigateToHome: () -> Unit
) {
    composable<OnBoarding> {
        OnBoardingRoute(
            padding = padding,
            navigateToHome = onNavigateToHome
        )
    }
}