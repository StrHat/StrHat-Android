package com.konkuk.strhat.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val MainWhite = Color(0xFFFFFFFF)
val MainBlack = Color(0xFF000000)

val Gray100 = Color(0xFFF3F4F6)
val Gray200 = Color(0xFFE5E7EB)
val Gray300 = Color(0xFFD0D0D0)
val Gray400 = Color(0xFFAAAAAA)
val Gray500 = Color(0xFF848484)
val Gray600 = Color(0xFF666668)
val Gray900 = Color(0xFF2F2F2F)

val MainBlue = Color(0xFF6BB2FF)
val SubBlue = Color(0xFFE1F0FF)

val MainRed = Color(0xFFFF0000)
val SubRed = Color(0xFFFF6B6B)

val MainYellow = Color(0xFFFFEE6B)
val SubYellow = Color(0xFFFCE64A)

val MainGreen = Color(0xFF85C47A)
val SubGreen = Color(0xFFC9FCA5)

@Immutable
data class StrHatColors(
    val MainWhite: Color,
    val MainBlack: Color,
    val Gray100: Color,
    val Gray200: Color,
    val Gray300: Color,
    val Gray400: Color,
    val Gray500: Color,
    val Gray600: Color,
    val Gray900: Color,
    val MainBlue: Color,
    val SubBlue: Color,
    val MainRed: Color,
    val SubRed: Color,
    val MainYellow: Color,
    val SubYellow: Color,
    val MainGreen: Color,
    val SubGreen: Color,
)

val defaultStrHatColors = StrHatColors(
    MainWhite = MainWhite,
    MainBlack = MainBlack,
    Gray100 = Gray100,
    Gray200 = Gray200,
    Gray300 = Gray300,
    Gray400 = Gray400,
    Gray500 = Gray500,
    Gray600 = Gray600,
    Gray900 = Gray900,
    MainBlue = MainBlue,
    SubBlue = SubBlue,
    MainRed = MainRed,
    SubRed = SubRed,
    MainYellow = MainYellow,
    SubYellow = SubYellow,
    MainGreen = MainGreen,
    SubGreen = SubGreen,
)

val LocalStrHatColorsProvider = staticCompositionLocalOf { defaultStrHatColors }