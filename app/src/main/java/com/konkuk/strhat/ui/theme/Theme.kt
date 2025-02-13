package com.konkuk.strhat.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object StrHatTheme {
    val colors: StrHatColors
        @Composable
        @ReadOnlyComposable
        get() = LocalStrHatColorsProvider.current

    val typography: StrHatTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalStrHatTypographyProvider.current
}

@Composable
fun ProvideStrHatColorsAndTypography(
    colors: StrHatColors,
    typography: StrHatTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalStrHatColorsProvider provides colors,
        LocalStrHatTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun StrHatTheme(
    content: @Composable () -> Unit
) {
    ProvideStrHatColorsAndTypography (
        colors = defaultStrHatColors,
        typography = defaultStrHatTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
                }
            }
        }
        MaterialTheme(
            content = content
        )
    }
}