package com.konkuk.strhat.feature.selfdiagnosis.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.feature.selfdiagnosis.SelfDiagnosisRoute

fun NavController.navigateToSelfDiagnosis(navOptions: NavOptions) {
    navigate(MainTabRoute.SelfDiagnosis, navOptions)
}

fun NavGraphBuilder.selfDiagnosisNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.SelfDiagnosis> {
        SelfDiagnosisRoute(padding)
    }
}