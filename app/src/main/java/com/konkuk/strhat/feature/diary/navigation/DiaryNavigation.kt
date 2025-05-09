package com.konkuk.strhat.feature.diary.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.konkuk.strhat.core.navigation.DiaryRoute
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.feature.diary.AddDiaryRoute
import com.konkuk.strhat.feature.diary.ChatRoute
import com.konkuk.strhat.feature.diary.DiaryAIFeedbackRecordRoute
import com.konkuk.strhat.feature.diary.DiaryAIFeedbackRoute
import com.konkuk.strhat.feature.diary.DiaryRoute
import com.konkuk.strhat.feature.diary.TodayStressScoreRoute

fun NavController.navigateToDiary(navOptions: NavOptions) {
    navigate(MainTabRoute.Diary, navOptions)
}

fun NavController.navigateToAddDiary() {
    navigate(DiaryRoute.AddDiary)
}

fun NavController.navigateToMyPageDiaryAIFeedback() {
    navigate(DiaryRoute.DiaryAIFeedback)
}

fun NavController.navigateToDiaryAIFeedback(date: String, summary: String, positiveKeywords: List<String>, negativeKeywords: List<String>, stressReliefSuggestions: String, diaryId: Int) {
    navigate(
        DiaryRoute.DiaryAIFeedback(date, summary, positiveKeywords, negativeKeywords, stressReliefSuggestions, diaryId)
    ) {
        popUpTo(MainTabRoute.Diary) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.navigateToDiaryAIFeedbackRecord(date: String) {
    navigate(DiaryRoute.DiaryAIFeedbackRecord(date))
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
    onNavigateToDiaryAIFeedback: (String, DiaryFeedbackModel) -> Unit,
    onNavigateToDiaryAIFeedbackRecord: (String) -> Unit,
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
            navigateToAddDiary = onNavigateToAddDiary,
            navigateToDiaryAIFeedback = onNavigateToDiaryAIFeedbackRecord
        )
    }

    composable<DiaryRoute.AddDiary> {
        AddDiaryRoute(
            padding = padding,
            navigateToDiaryAIFeedback = onNavigateToDiaryAIFeedback
        )
    }

    composable<DiaryRoute.DiaryAIFeedback> {navBackStackEntry ->
        val summary = navBackStackEntry.toRoute<DiaryRoute.DiaryAIFeedback>().summary
        val positiveKeywords = navBackStackEntry.toRoute<DiaryRoute.DiaryAIFeedback>().positiveKeywords
        val negativeKeywords = navBackStackEntry.toRoute<DiaryRoute.DiaryAIFeedback>().negativeKeywords
        val stressReliefSuggestions = navBackStackEntry.toRoute<DiaryRoute.DiaryAIFeedback>().stressReliefSuggestions
        val diaryId = navBackStackEntry.toRoute<DiaryRoute.DiaryAIFeedback>().diaryId
        val date = navBackStackEntry.toRoute<DiaryRoute.DiaryAIFeedback>().date

        val diaryFeedbackModel = DiaryFeedbackModel(
            summary = summary,
            positiveKeywords = positiveKeywords,
            negativeKeywords = negativeKeywords,
            stressReliefSuggestions = stressReliefSuggestions,
            diaryId = diaryId
        )

        DiaryAIFeedbackRoute(
            padding = padding,
            date = date,
            diaryFeedbackModel = diaryFeedbackModel,
            navigateToChat = onNavigateToChat,
            navigateToTodayStressScore = onNavigateToTodayStressScore,
            popBackStack = onPopBackStack,
            navigateToMyPageChatHistory = onNavigateToMyPageChatHistory,
            navController = navController
        )
    }

    composable<DiaryRoute.DiaryAIFeedbackRecord> { navBackStackEntry ->
        val date = navBackStackEntry.toRoute<DiaryRoute.DiaryAIFeedbackRecord>().date

        DiaryAIFeedbackRecordRoute(
            padding = padding,
            date = date,
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