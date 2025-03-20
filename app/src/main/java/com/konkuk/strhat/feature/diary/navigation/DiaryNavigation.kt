package com.konkuk.strhat.feature.diary.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.DiaryRoute
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.core.navigation.Route
import com.konkuk.strhat.feature.diary.AddDiaryRoute
import com.konkuk.strhat.feature.diary.ChatRoute
import com.konkuk.strhat.feature.diary.DiaryAIFeedbackRoute
import com.konkuk.strhat.feature.diary.DiaryRoute
import com.konkuk.strhat.feature.diary.StressScoreRoute

fun NavController.navigateToDiary(navOptions: NavOptions) {
    navigate(MainTabRoute.Diary, navOptions)
}

fun NavController.navigateToAddDiary() {
    navigate(Route.AddDiary)
}

fun NavController.navigateToDiaryAIFeedback() {
    navigate(DiaryRoute.DiaryAIFeedback) {
        popUpTo(MainTabRoute.Diary) {
            inclusive = false
        }
        launchSingleTop = true
    }
}

fun NavController.navigateToChat() {
    navigate(DiaryRoute.Chat)
}

fun NavController.navigateToStressScore() {
    navigate(DiaryRoute.StressScore)
}

fun NavGraphBuilder.diaryNavGraph(
    padding: PaddingValues,
    onNavigateToAddDiary: () -> Unit,
    onNavigateToDiaryAIFeedback: () -> Unit,
    onNavigateToChat: () -> Unit,
    popBackStack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToStressScore: () -> Unit
) {
    composable<MainTabRoute.Diary> {
        DiaryRoute(
            padding = padding,
            navigateToAddDiary = onNavigateToAddDiary
        )
    }

    composable<Route.AddDiary> {
        AddDiaryRoute(
            padding = padding,
            navigateToDiaryAIFeedback = onNavigateToDiaryAIFeedback
        )
    }

    composable<DiaryRoute.DiaryAIFeedback> {
        DiaryAIFeedbackRoute(
            padding = padding,
            navigateToChat = onNavigateToChat,
            navigateToDiaryMain = popBackStack
        )
    }

    composable<DiaryRoute.Chat> {
        ChatRoute(
            padding = padding,
            navigateToStressScore = onNavigateToStressScore
        )
    }

    composable<DiaryRoute.StressScore> {
        StressScoreRoute(
            padding = padding,
            navigateToHome = onNavigateToHome
        )
    }
}