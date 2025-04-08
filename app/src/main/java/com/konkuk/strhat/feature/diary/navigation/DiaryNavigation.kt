package com.konkuk.strhat.feature.diary.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.DiaryRoute
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.feature.diary.AddDiaryRoute
import com.konkuk.strhat.feature.diary.ChatRoute
import com.konkuk.strhat.feature.diary.DiaryAIFeedbackRoute
import com.konkuk.strhat.feature.diary.DiaryRoute
import com.konkuk.strhat.feature.diary.TodayStressScoreRoute

fun NavController.navigateToDiary(navOptions: NavOptions) {
    navigate(MainTabRoute.Diary, navOptions)
}

fun NavController.navigateToAddDiary() {
    navigate(DiaryRoute.AddDiary)
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

fun NavController.navigateToTodayStressScore() {
    navigate(DiaryRoute.TodayStressScore)
}

fun NavGraphBuilder.diaryNavGraph(
    padding: PaddingValues,
    onNavigateToAddDiary: () -> Unit,
    onNavigateToDiaryAIFeedback: () -> Unit,
    onNavigateToChat: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToMyPage: () -> Unit,
    onNavigateToTodayStressScore: () -> Unit,
    onPopBackStack: () -> Unit,
    onNavigateToMyPageChatHistory: () -> Unit,
    navController: NavController
) {
    composable<MainTabRoute.Diary> {
        DiaryRoute(
            padding = padding,
            navigateToAddDiary = onNavigateToAddDiary
        )
    }

    composable<DiaryRoute.AddDiary> {
        AddDiaryRoute(
            padding = padding,
            navigateToDiaryAIFeedback = onNavigateToDiaryAIFeedback
        )
    }

    composable<DiaryRoute.DiaryAIFeedback> {
        DiaryAIFeedbackRoute(
            padding = padding,
            navigateToChat = onNavigateToChat,
            navigateToTodayStressScore = onNavigateToTodayStressScore,
            popBackStack = onPopBackStack,
            navigateToMyPageChatHistory = onNavigateToMyPageChatHistory,
            navController = navController
        )
    }

    composable<DiaryRoute.Chat> {
        ChatRoute(
            padding = padding,
            navigateToTodayStressScore = onNavigateToTodayStressScore
        )
    }

    composable<DiaryRoute.TodayStressScore> {
        TodayStressScoreRoute(
            padding = padding,
            navigateToHome = onNavigateToHome,
            navigateToMyPage = onNavigateToMyPage,
            navController = navController
        )
    }
}