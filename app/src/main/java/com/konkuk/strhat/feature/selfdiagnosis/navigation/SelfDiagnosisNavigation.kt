package com.konkuk.strhat.feature.selfdiagnosis.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.core.navigation.SelfDiagnosisRoute
import com.konkuk.strhat.feature.selfdiagnosis.SelfDiagnosisResultRoute
import com.konkuk.strhat.feature.selfdiagnosis.SelfDiagnosisRoute
import com.konkuk.strhat.feature.selfdiagnosis.SelfDiagnosisTestRoute

fun NavController.navigateToSelfDiagnosis(navOptions: NavOptions) {
    navigate(MainTabRoute.SelfDiagnosis, navOptions)
}

fun NavController.navigateToSelfDiagnosisTest(type: String) {
    navigate(SelfDiagnosisRoute.SelfDiagnosisTest(type))
}

fun NavController.navigateToSelfDiagnosisResult() {
    navigate(SelfDiagnosisRoute.SelfDiagnosisResult)
}

fun NavGraphBuilder.selfDiagnosisNavGraph(
    padding: PaddingValues,
    onNavigateToSelfDiagnosisTest: (String) -> Unit,
    onNavigateToSelfDiagnosisResult: () -> Unit,
    onNavigateToSelfDiagnosis: () -> Unit
) {
    composable<MainTabRoute.SelfDiagnosis> {
        SelfDiagnosisRoute(
            padding = padding,
            navigateToSelfDiagnosisTest = onNavigateToSelfDiagnosisTest
        )
    }

    composable<SelfDiagnosisRoute.SelfDiagnosisTest> { navBackStackEntry ->
        val type = navBackStackEntry.toRoute<SelfDiagnosisRoute.SelfDiagnosisTest>().type

        SelfDiagnosisTestRoute(
            padding = padding,
            type = type,
            navigateToSelfDiagnosisResult = onNavigateToSelfDiagnosisResult
        )
    }

    composable<SelfDiagnosisRoute.SelfDiagnosisResult> {
        SelfDiagnosisResultRoute(
            padding = padding,
            navigateToSelfDiagnosis = onNavigateToSelfDiagnosis
        )
    }
}