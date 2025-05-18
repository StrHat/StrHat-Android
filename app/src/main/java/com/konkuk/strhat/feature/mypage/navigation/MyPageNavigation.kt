package com.konkuk.strhat.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.core.navigation.MyPageRoute
import com.konkuk.strhat.feature.mypage.MyAccountRoute
import com.konkuk.strhat.feature.mypage.MyHealingRoute
import com.konkuk.strhat.feature.mypage.MyPageChatHistoryRoute
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

fun NavController.navigateToMySelfDiagnosisRecordResult(type: String) {
    navigate(MyPageRoute.MySelfDiagnosisRecordResult(type))
}

fun NavController.navigateToChangeGraph(date: String, nickname: String) {
    navigate(MyPageRoute.MyStressEmotionChangeGraph(date, nickname))
}

fun NavController.navigateToMyPageStressScore(date: String) {
    navigate(MyPageRoute.MyPageStressScore(date))
}

fun NavController.navigateToMyPageChatHistory(diaryId: Int) {
    navigate(MyPageRoute.MyPageChatHistory(diaryId))
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    onNavigateToMyPage: () -> Unit,
    onNavigateToMySelfDiagnosisRecord: () -> Unit,
    onNavigateToMySelfDiagnosisRecordResult: (String) -> Unit,
    onNavigateToChangeGraph: (String, String) -> Unit,
    onPopBackStack: () -> Unit,
    onNavigateToMyPageStressScore: (String) -> Unit,
    onNavigateToMyPageAIFeedback: (String) -> Unit,
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
            navigateToMyPageStressScore = onNavigateToMyPageStressScore,
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

    composable<MyPageRoute.MySelfDiagnosisRecordResult> { navBackStackEntry ->
        val type = navBackStackEntry.toRoute<MyPageRoute.MySelfDiagnosisRecordResult>().type

        MySelfDiagnosisRecordResultRoute(
            padding = padding,
            type = type,
            navigateToMyPage = onNavigateToMyPage
        )
    }

    composable<MyPageRoute.MyStressEmotionChangeGraph> { navBackStackEntry ->
        val date = navBackStackEntry.toRoute<MyPageRoute.MyStressEmotionChangeGraph>().date
        val nickname = navBackStackEntry.toRoute<MyPageRoute.MyStressEmotionChangeGraph>().nickname

        MyStressEmotionChangeGraphRoute(
            padding = padding,
            date = date,
            nickname = nickname,
            navigateToMyPageStressScore = onNavigateToMyPageStressScore,
            navigateToMyPageAIFeedback = onNavigateToMyPageAIFeedback
        )
    }

    composable<MyPageRoute.MyPageStressScore> { navBackStackEntry ->
        val date = navBackStackEntry.toRoute<MyPageRoute.MyPageStressScore>().date

        MyPageStressScoreRoute(
            padding = padding,
            date = date,
            popBackStack = onPopBackStack
        )
    }

    composable<MyPageRoute.MyPageChatHistory> { navBackStackEntry ->
        val diaryId = navBackStackEntry.toRoute<MyPageRoute.MyPageChatHistory>().diaryId

        MyPageChatHistoryRoute(
            padding = padding,
            diaryId = diaryId,
            popBackStack = onPopBackStack
        )
    }
}