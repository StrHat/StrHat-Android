package com.konkuk.strhat.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.core.navigation.MyPageRoute
import com.konkuk.strhat.feature.mypage.MyAccountRoute
import com.konkuk.strhat.feature.mypage.MyHealingRoute
import com.konkuk.strhat.feature.mypage.MyPageRoute
import com.konkuk.strhat.feature.mypage.MyPersonalityRoute
import com.konkuk.strhat.feature.mypage.MyStressRoute

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavController.navigateToAccount() {
    navigate(MyPageRoute.Account)
}

fun NavController.navigateToHealing() {
    navigate(MyPageRoute.Healing)
}

fun NavController.navigateToStress() {
    navigate(MyPageRoute.Stress)
}

fun NavController.navigateToPersonality() {
    navigate(MyPageRoute.Personality)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    onNavigateToMyPage: () -> Unit,
    navController: NavController
) {
    composable<MainTabRoute.MyPage> {
        MyPageRoute(
            padding = padding,
            navigateToAccount = navController::navigateToAccount,
            navigateToHealing = navController::navigateToHealing,
            navigateToStress = navController::navigateToStress,
            navigateToPersonality = navController::navigateToPersonality
        )
    }

    composable<MyPageRoute.Account> {
        MyAccountRoute(
            padding = padding,
            navigateToMyPage = onNavigateToMyPage
        )
    }

    composable<MyPageRoute.Healing> {
        MyHealingRoute(
            padding = padding,
            navigateToMyPage = onNavigateToMyPage
        )
    }

    composable<MyPageRoute.Stress> {
        MyStressRoute(
            padding = padding,
            navigateToMyPage = onNavigateToMyPage
        )
    }

    composable<MyPageRoute.Personality> {
        MyPersonalityRoute(
            padding = padding,
            navigateToMyPage = onNavigateToMyPage
        )
    }
}