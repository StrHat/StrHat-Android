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
import com.konkuk.strhat.core.navigation.Route
import com.konkuk.strhat.feature.diary.navigation.navigateToAddDiary
import com.konkuk.strhat.feature.diary.navigation.navigateToDiary
import com.konkuk.strhat.feature.home.navigation.navigateToHome
import com.konkuk.strhat.feature.login.navigation.navigateToLogin
import com.konkuk.strhat.feature.mypage.navigation.navigateToMyPage
import com.konkuk.strhat.feature.onboarding.navigation.navigateToOnBoarding
import com.konkuk.strhat.feature.selfdiagnosis.navigation.navigateToSelfDiagnosis

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
        navController.navigateToLogin()
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

    fun navigateToAddDiary() {
        navController.navigateToAddDiary()
    }

    private fun popBackStack() {
        navController.popBackStack()
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