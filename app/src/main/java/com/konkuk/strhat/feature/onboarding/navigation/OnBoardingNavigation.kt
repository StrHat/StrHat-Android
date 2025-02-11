package com.konkuk.strhat.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.konkuk.strhat.feature.onboarding.OnBoardingRoute
import com.konkuk.strhat.core.navigation.Route.OnBoarding as OnBoardingRoute

fun NavController.navigateToOnBoarding() {
    navigate(OnBoardingRoute)
}

fun NavGraphBuilder.onBoardingNavGraph(
    padding: PaddingValues,
    onNavigateToHome: () -> Unit
) {
    composable<OnBoardingRoute> {
        OnBoardingRoute(
            padding = padding,
            navigateToHome = onNavigateToHome
        )
    }
}