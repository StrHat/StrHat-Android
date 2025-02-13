package com.konkuk.strhat.feature.diary.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.feature.diary.DiaryRoute

fun NavController.navigateToDiary(navOptions: NavOptions) {
    navigate(MainTabRoute.Diary, navOptions)
}

fun NavGraphBuilder.diaryNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.Diary> {
        DiaryRoute(padding)
    }
}