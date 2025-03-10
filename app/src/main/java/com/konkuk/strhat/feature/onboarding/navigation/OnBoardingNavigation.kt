package com.konkuk.strhat.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.konkuk.strhat.core.navigation.OnBoardingRoute
import com.konkuk.strhat.core.navigation.Route.OnBoarding
import com.konkuk.strhat.feature.onboarding.OnBoardingGenderRoute
import com.konkuk.strhat.feature.onboarding.OnBoardingNickNameRoute

fun NavController.navigateToOnBoarding() {
    navigate(OnBoarding::class.qualifiedName!!)
}

fun NavController.navigateToNickName() {
    navigate(OnBoardingRoute.NickName::class.qualifiedName!!)
}

fun NavController.navigateToGender() {
    navigate(OnBoardingRoute.Gender::class.qualifiedName!!)
}

fun NavController.navigateToHobby() {
    navigate(OnBoardingRoute.Hobby::class.qualifiedName!!)
}

fun NavController.navigateToStress() {
    navigate(OnBoardingRoute.Stress::class.qualifiedName!!)
}

fun NavController.navigateToPersonality() {
    navigate(OnBoardingRoute.Personality::class.qualifiedName!!)
}

fun NavController.navigateToSuccess() {
    navigate(OnBoardingRoute.Success::class.qualifiedName!!)
}

fun NavGraphBuilder.onBoardingNavGraph(
    padding: PaddingValues,
    navController: NavController
) {
    navigation(
        startDestination = OnBoardingRoute.NickName::class.qualifiedName!!,
        route = OnBoarding::class.qualifiedName!!
    ) {
        composable(OnBoardingRoute.NickName::class.qualifiedName!!) {
            OnBoardingNickNameRoute(
                padding = padding,
                navigateToGender = navController::navigateToGender
            )
        }

        composable(OnBoardingRoute.Gender::class.qualifiedName!!) {
            OnBoardingGenderRoute(
                padding = padding,
                navigateToHobby = navController::navigateToHobby
            )
        }
    }
}