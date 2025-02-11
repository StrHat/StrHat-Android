package com.konkuk.strhat.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object OnBoarding : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute
    @Serializable
    data object Diary : MainTabRoute
    @Serializable
    data object SelfDiagnosis : MainTabRoute
    @Serializable
    data object MyPage : MainTabRoute
}