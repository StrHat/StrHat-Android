package com.konkuk.strhat.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.OnBoardingRoute
import com.konkuk.strhat.feature.onboarding.OnBoardingGenderRoute
import com.konkuk.strhat.feature.onboarding.OnBoardingHobbyRoute
import com.konkuk.strhat.feature.onboarding.OnBoardingNickNameRoute
import com.konkuk.strhat.feature.onboarding.OnBoardingPersonalityRoute
import com.konkuk.strhat.feature.onboarding.OnBoardingStressRoute
import com.konkuk.strhat.feature.onboarding.OnBoardingSuccessRoute

fun NavController.navigateToOnBoarding() {
    navigate(OnBoardingRoute.NickName)
}

fun NavController.navigateToGender() {
    navigate(OnBoardingRoute.Gender)
}

fun NavController.navigateToHobby() {
    navigate(OnBoardingRoute.Hobby)
}

fun NavController.navigateToStress() {
    navigate(OnBoardingRoute.Stress)
}

fun NavController.navigateToPersonality() {
    navigate(OnBoardingRoute.Personality)
}

fun NavController.navigateToSuccess() {
    navigate(OnBoardingRoute.Success)
}

fun NavGraphBuilder.onBoardingNavGraph(
    padding: PaddingValues,
    onNavigateToHome: () -> Unit,
    navController: NavController
) {
    composable<OnBoardingRoute.NickName> {
        OnBoardingNickNameRoute(
            padding = padding,
            navigateToGender = navController::navigateToGender
        )
    }

    composable<OnBoardingRoute.Gender> {
        OnBoardingGenderRoute(
            padding = padding,
            navigateToHobby = navController::navigateToHobby
        )
    }

    composable<OnBoardingRoute.Hobby> {
        OnBoardingHobbyRoute(
            padding = padding,
            navigateToStress = navController::navigateToStress
        )
    }

    composable<OnBoardingRoute.Stress> {
        OnBoardingStressRoute(
            padding = padding,
            navigateToPersonality = navController::navigateToPersonality
        )
    }

    composable<OnBoardingRoute.Personality> {
        OnBoardingPersonalityRoute(
            padding = padding,
            navigateToSuccess = navController::navigateToSuccess
        )
    }

    composable<OnBoardingRoute.Success> {
        OnBoardingSuccessRoute(
            padding = padding,
            navigateToHome = onNavigateToHome
        )
    }
}
