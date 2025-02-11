package com.konkuk.strhat.feature.main.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.konkuk.strhat.feature.diary.navigation.diaryNavGraph
import com.konkuk.strhat.feature.home.navigation.homeNavGraph
import com.konkuk.strhat.feature.main.MainNavigator
import com.konkuk.strhat.feature.mypage.navigation.myPageNavGraph
import com.konkuk.strhat.feature.onboarding.navigation.onBoardingNavGraph
import com.konkuk.strhat.feature.selfdiagnosis.navigation.selfDiagnosisNavGraph

@SuppressLint("UnrememberedGetBackStackEntry")
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
            )

            selfDiagnosisNavGraph(
                padding = padding,
            )

            myPageNavGraph(
                padding = padding,
            )

            onBoardingNavGraph(
                padding = padding,
                onNavigateToHome = navigator::navigateToHome
            )
        }
    }
}