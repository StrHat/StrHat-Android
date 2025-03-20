package com.konkuk.strhat.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Splash : Route
    @Serializable
    data object Login : Route
    @Serializable
    data object OnBoarding : Route
    @Serializable
    data object AddDiary : Route
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

sealed interface OnBoardingRoute: Route{
    @Serializable
    data object NickName : OnBoardingRoute
    @Serializable
    data object Gender : OnBoardingRoute
    @Serializable
    data object Hobby : OnBoardingRoute
    @Serializable
    data object Stress : OnBoardingRoute
    @Serializable
    data object Personality : OnBoardingRoute
    @Serializable
    data object Success : OnBoardingRoute
}

sealed interface MyPageRoute: Route{
    @Serializable
    data object Account: MyPageRoute
    @Serializable
    data object Healing: MyPageRoute
    @Serializable
    data object Stress: MyPageRoute
    @Serializable
    data object Personality: MyPageRoute
}