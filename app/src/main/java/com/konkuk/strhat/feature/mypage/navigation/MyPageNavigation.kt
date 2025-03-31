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
import com.konkuk.strhat.feature.mypage.MyPageStressScoreRoute
import com.konkuk.strhat.feature.mypage.MyPersonalityRoute
import com.konkuk.strhat.feature.mypage.MySelfDiagnosisRecordResultRoute
import com.konkuk.strhat.feature.mypage.MySelfDiagnosisRecordRoute
import com.konkuk.strhat.feature.mypage.MyStressEmotionChangeGraphRoute
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

fun NavController.navigateToMySelfDiagnosisRecord() {
    navigate(MyPageRoute.MySelfDiagnosisRecord)
}

fun NavController.navigateToMySelfDiagnosisRecordResult() {
    navigate(MyPageRoute.MySelfDiagnosisRecordResult)
}

fun NavController.navigateToChangeGraph() {
    navigate(MyPageRoute.MyStressEmotionChangeGraph)
}

fun NavController.navigateToMyPageStressScore() {
    navigate(MyPageRoute.MyPageStressScore)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    onNavigateToMyPage: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToMySelfDiagnosisRecord: () -> Unit,
    onNavigateToMySelfDiagnosisRecordResult: () -> Unit,
    onNavigateToTodayStressScore: () -> Unit,
    onNavigateToChangeGraph: () -> Unit,
    onPopBackStack: () -> Unit,
    onNavigateToMyPageStressScore: () -> Unit,
    navController: NavController
) {
    composable<MainTabRoute.MyPage> {
        MyPageRoute(
            padding = padding,
            navigateToAccount = navController::navigateToAccount,
            navigateToHealing = navController::navigateToHealing,
            navigateToStress = navController::navigateToStress,
            navigateToPersonality = navController::navigateToPersonality,
            navigateToMySelfDiagnosisRecord = onNavigateToMySelfDiagnosisRecord,
            navigateToLogin = onNavigateToLogin,
            navigateToTodayStressScore = onNavigateToTodayStressScore,
            navigateToChangeGraph = onNavigateToChangeGraph
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

    composable<MyPageRoute.MySelfDiagnosisRecord> {
        MySelfDiagnosisRecordRoute(
            padding = padding,
            navigateToMySelfDiagnosisRecordResult = onNavigateToMySelfDiagnosisRecordResult
        )
    }

    composable<MyPageRoute.MySelfDiagnosisRecordResult> {
        MySelfDiagnosisRecordResultRoute(
            padding = padding,
            navigateToMyPage = onNavigateToMyPage
        )
    }

    composable<MyPageRoute.MyStressEmotionChangeGraph> {
        MyStressEmotionChangeGraphRoute(
            padding = padding,
            navigateToMyPageStressScore = onNavigateToMyPageStressScore
        )
    }

    composable<MyPageRoute.MyPageStressScore> {
        MyPageStressScoreRoute(
            padding = padding,
            popBackStack = onPopBackStack
        )
    }
}