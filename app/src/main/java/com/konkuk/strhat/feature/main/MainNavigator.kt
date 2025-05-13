package com.konkuk.strhat.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.core.navigation.Route
import com.konkuk.strhat.domain.type.ChatModeType
import com.konkuk.strhat.feature.diary.navigation.navigateToAddDiary
import com.konkuk.strhat.feature.diary.navigation.navigateToChat
import com.konkuk.strhat.feature.diary.navigation.navigateToDiary
import com.konkuk.strhat.feature.diary.navigation.navigateToDiaryAIFeedback
import com.konkuk.strhat.feature.diary.navigation.navigateToDiaryAIFeedbackRecord
import com.konkuk.strhat.feature.diary.navigation.navigateToMyPageDiaryAIFeedback
import com.konkuk.strhat.feature.diary.navigation.navigateToTodayStressScore
import com.konkuk.strhat.feature.home.navigation.navigateToHome
import com.konkuk.strhat.feature.login.navigation.navigateToLogin
import com.konkuk.strhat.feature.mypage.navigation.navigateToChangeGraph
import com.konkuk.strhat.feature.mypage.navigation.navigateToMyPage
import com.konkuk.strhat.feature.mypage.navigation.navigateToMyPageChatHistory
import com.konkuk.strhat.feature.mypage.navigation.navigateToMyPageStressScore
import com.konkuk.strhat.feature.mypage.navigation.navigateToMySelfDiagnosisRecord
import com.konkuk.strhat.feature.mypage.navigation.navigateToMySelfDiagnosisRecordResult
import com.konkuk.strhat.feature.onboarding.navigation.navigateToOnBoarding
import com.konkuk.strhat.feature.selfdiagnosis.navigation.navigateToSelfDiagnosis
import com.konkuk.strhat.feature.selfdiagnosis.navigation.navigateToSelfDiagnosisResult
import com.konkuk.strhat.feature.selfdiagnosis.navigation.navigateToSelfDiagnosisTest

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Route.Splash

    val currentTab: MainTab?
        @Composable get() = MainTab.entries.find { tab ->
            when (tab.route) {
                else -> currentDestination?.route == tab.route::class.qualifiedName
            }
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(MainTab.HOME.route) {
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.DIARY -> navController.navigateToDiary(navOptions)
            MainTab.SELFDIAGNOSIS -> navController.navigateToSelfDiagnosis(navOptions)
            MainTab.MYPAGE -> navController.navigateToMyPage(navOptions)
        }
    }

    fun navigateToLogin() {
        navController.navigateToLogin(
            navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToOnBoarding() {
        navController.navigateToOnBoarding()
    }

    fun navigateToHome(navOptions: NavOptions? = null) {
        navController.navigateToHome(
            navOptions ?: navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToDiary(navOptions: NavOptions? = null) {
        navController.navigateToDiary(
            navOptions ?: navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToSelfDiagnosis(navOptions: NavOptions? = null) {
        navController.navigateToSelfDiagnosis(
            navOptions ?: navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToMyPage(navOptions: NavOptions? = null) {
        navController.navigateToMyPage(
            navOptions ?: navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = false
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToAddDiary() {
        navController.navigateToAddDiary()
    }

    fun navigateToMyPageDiaryAIFeedback() {
        navController.navigateToMyPageDiaryAIFeedback()
    }

    fun navigateToDiaryAIFeedback(
        date: String,
        summary: String,
        positiveKeywords: List<String>,
        negativeKeywords: List<String>,
        stressReliefSuggestions: String,
        diaryId: Int
    ) {
        navController.navigateToDiaryAIFeedback(
            date = date,
            summary = summary,
            positiveKeywords = positiveKeywords,
            negativeKeywords = negativeKeywords,
            stressReliefSuggestions = stressReliefSuggestions,
            diaryId = diaryId
        )
    }

    fun navigateToChat(diaryId: Int, date: String, chatMode: ChatModeType) {
        navController.navigateToChat(diaryId, date, chatMode)
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackInclusiveFalse() {
        navController.popBackStack(MainTabRoute.Diary, false)
    }

    fun navigateToTodayStressScore(date: String) {
        navController.navigateToTodayStressScore(date)
    }

    fun navigateToChangeGraph() {
        navController.navigateToChangeGraph()
    }

    fun navigateToSelfDiagnosisTest() {
        navController.navigateToSelfDiagnosisTest()
    }

    fun navigateToSelfDiagnosisResult() {
        navController.navigateToSelfDiagnosisResult()
    }

    fun navigateToMySelfDiagnosisRecord() {
        navController.navigateToMySelfDiagnosisRecord()
    }

    fun navigateToMySelfDiagnosisRecordResult() {
        navController.navigateToMySelfDiagnosisRecordResult()
    }

    fun navigateToMyPageStressScore() {
        navController.navigateToMyPageStressScore()
    }

    fun navigateToMyPageChatHistory(diaryId: Int) {
        navController.navigateToMyPageChatHistory(diaryId)
    }

    fun navigateToDiaryAIFeedbackRecord(date: String) {
        navController.navigateToDiaryAIFeedbackRecord(date)
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean =
        navController.currentDestination?.route == T::class.qualifiedName

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.route == it::class.qualifiedName
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}