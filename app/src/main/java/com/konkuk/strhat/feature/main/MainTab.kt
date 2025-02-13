package com.konkuk.strhat.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.konkuk.strhat.R
import com.konkuk.strhat.core.navigation.MainTabRoute
import com.konkuk.strhat.core.navigation.Route

enum class MainTab(
    @DrawableRes val defaultIconResId: Int,
    @DrawableRes val selectIconResId: Int,
    @StringRes val descriptionResId: Int,
    val route: MainTabRoute,
) {
    HOME(
        defaultIconResId = R.drawable.ic_home_default,
        selectIconResId =  R.drawable.ic_home_selected,
        descriptionResId = R.string.home,
        route = MainTabRoute.Home,
    ),
    DIARY(
        defaultIconResId = R.drawable.ic_diary_default,
        selectIconResId =  R.drawable.ic_diary_selected,
        descriptionResId = R.string.diary,
        route = MainTabRoute.Diary
    ),
    SELFDIAGNOSIS(
        defaultIconResId = R.drawable.ic_self_diagnosis_default,
        selectIconResId =  R.drawable.ic_self_diagnosis_selected,
        descriptionResId = R.string.self_diagnosis,
        route = MainTabRoute.SelfDiagnosis
    ),
    MYPAGE(
        defaultIconResId = R.drawable.ic_my_page_default,
        selectIconResId =  R.drawable.ic_my_page_selected,
        descriptionResId = R.string.my_page,
        route = MainTabRoute.MyPage,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}