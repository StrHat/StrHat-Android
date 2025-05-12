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
import com.konkuk.strhat.feature.onboarding.OnBoardingViewModel

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
    navController: NavController,
    viewModel: OnBoardingViewModel
) {
    composable<OnBoardingRoute.NickName> {
        OnBoardingNickNameRoute(
            padding = padding,
            navigateToGender = navController::navigateToGender,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.Gender> {
        OnBoardingGenderRoute(
            padding = padding,
            navigateToHobby = navController::navigateToHobby,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.Hobby> {
        OnBoardingHobbyRoute(
            padding = padding,
            navigateToStress = navController::navigateToStress,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.Stress> {
        OnBoardingStressRoute(
            padding = padding,
            navigateToPersonality = navController::navigateToPersonality,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.Personality> {
        OnBoardingPersonalityRoute(
            padding = padding,
            navigateToSuccess = navController::navigateToSuccess,
            viewModel = viewModel
        )
    }

    composable<OnBoardingRoute.Success> {
        OnBoardingSuccessRoute(
            padding = padding,
            navigateToHome = onNavigateToHome,
            viewModel = viewModel
        )
    }
}
