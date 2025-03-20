package com.konkuk.strhat.feature.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.compose.NavHost
import com.konkuk.strhat.feature.diary.navigation.diaryNavGraph
import com.konkuk.strhat.feature.home.navigation.homeNavGraph
import com.konkuk.strhat.feature.login.navigation.loginNavGraph
import com.konkuk.strhat.feature.main.MainNavigator
import com.konkuk.strhat.feature.mypage.navigation.myPageNavGraph
import com.konkuk.strhat.feature.onboarding.navigation.onBoardingNavGraph
import com.konkuk.strhat.feature.selfdiagnosis.navigation.selfDiagnosisNavGraph
import com.konkuk.strhat.feature.splash.navigation.splashNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding,
            )

            diaryNavGraph(
                padding = padding,
                onNavigateToAddDiary = navigator::navigateToAddDiary,
                onNavigateToDiaryAIFeedback = navigator::navigateToDiaryAIFeedback,
                onNavigateToChat = navigator::navigateToChat,
                popBackStack = navigator::popBackStackInclusiveFalse,
                onNavigateToHome = navigator::navigateToHome
            )

            selfDiagnosisNavGraph(
                padding = padding,
            )

            myPageNavGraph(
                padding = padding,
            )

            splashNavGraph(
                padding = padding,
                onNavigateToLogin = navigator::navigateToLogin
            )

            loginNavGraph(
                padding = padding,
                onNavigateToOnBoarding = navigator::navigateToOnBoarding
            )

            onBoardingNavGraph(
                padding = padding,
                onNavigateToHome = navigator::navigateToHome,
                navController = navigator.navController
            )
        }
    }
}