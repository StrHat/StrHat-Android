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
import com.konkuk.strhat.domain.type.ChatModeType
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

fun NavController.navigateToMyPageDiaryAIFeedback(date: String) {
    navigate(DiaryRoute.DiaryAIFeedbackRecord(date))
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

fun NavController.navigateToChat(diaryId: Int, date: String, chatMode: ChatModeType) {
    navigate(DiaryRoute.Chat(diaryId, date, chatMode))
}

fun NavController.navigateToTodayStressScore(date: String) {
    navigate(DiaryRoute.TodayStressScore(date))
}

fun NavGraphBuilder.diaryNavGraph(
    padding: PaddingValues,
    onNavigateToAddDiary: () -> Unit,
    onNavigateToDiaryAIFeedback: (String, DiaryFeedbackModel) -> Unit,
    onNavigateToDiaryAIFeedbackRecord: (String) -> Unit,
    onNavigateToChat: (Int, String, ChatModeType) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToMyPage: () -> Unit,
    onNavigateToTodayStressScore: (String) -> Unit,
    onPopBackStack: () -> Unit,
    onNavigateToMyPageChatHistory: (Int) -> Unit,
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

    composable<DiaryRoute.Chat> { navBackStackEntry ->
        val diaryId = navBackStackEntry.toRoute<DiaryRoute.Chat>().diaryId
        val date = navBackStackEntry.toRoute<DiaryRoute.Chat>().date
        val chatMode = navBackStackEntry.toRoute<DiaryRoute.Chat>().chatMode

        ChatRoute(
            padding = padding,
            diaryId = diaryId,
            date = date,
            chatMode = chatMode,
            navigateToTodayStressScore = onNavigateToTodayStressScore
        )
    }

    composable<DiaryRoute.TodayStressScore> { navBackStackEntry ->
        val date = navBackStackEntry.toRoute<DiaryRoute.TodayStressScore>().date

        TodayStressScoreRoute(
            padding = padding,
            date = date,
            navigateToHome = onNavigateToHome,
            navigateToMyPage = onNavigateToMyPage,
            navController = navController
        )
    }
}