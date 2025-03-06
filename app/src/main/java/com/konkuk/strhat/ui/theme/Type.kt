package com.konkuk.strhat.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.konkuk.strhat.R

val strHatFontBold = FontFamily(Font(R.font.roboto_bold))
val strHatFontExtraBold = FontFamily(Font(R.font.roboto_extrabold))
val strHatFontMedium = FontFamily(Font(R.font.roboto_medium))
val strHatFontRegular = FontFamily(Font(R.font.roboto_regular))

@Immutable
data class StrHatTypography(
    val head1_b_24: TextStyle,
    val head2_b_20: TextStyle,
    val head2_r_20: TextStyle,
    val title1_b_18: TextStyle,
    val body1_b_16: TextStyle,
    val body1_m_16: TextStyle,
    val body1_r_16: TextStyle,
    val body2_r_15: TextStyle,
    val body3_b_14: TextStyle,
    val body3_m_14: TextStyle,
    val body3_r_14: TextStyle,
    val body4_b_12: TextStyle,
    val body4_m_12: TextStyle,
    val body4_r_12: TextStyle,
    val body5_b_10: TextStyle,
)

val defaultStrHatTypography = StrHatTypography(
    head1_b_24 = TextStyle(
        fontFamily = strHatFontBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    head2_b_20 = TextStyle(
        fontFamily = strHatFontBold,
        fontSize = 20.sp,
        lineHeight = 22.sp
    ),
    head2_r_20 = TextStyle(
        fontFamily = strHatFontRegular,
        fontSize = 20.sp,
        lineHeight = 22.sp
    ),
    title1_b_18 = TextStyle(
        fontFamily = strHatFontBold,
        fontSize = 18.sp,
        lineHeight = 20.sp
    ),
    body1_b_16 = TextStyle(
        fontFamily = strHatFontExtraBold,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    body1_m_16 = TextStyle(
        fontFamily = strHatFontMedium,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    body1_r_16 = TextStyle(
        fontFamily = strHatFontRegular,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    body2_r_15 = TextStyle(
        fontFamily = strHatFontRegular,
        fontSize = 15.sp,
        lineHeight = 17.sp
    ),
    body3_b_14 = TextStyle(
        fontFamily = strHatFontBold,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    body3_m_14 = TextStyle(
        fontFamily = strHatFontMedium,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    body3_r_14 = TextStyle(
        fontFamily = strHatFontRegular,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    body4_b_12 = TextStyle(
        fontFamily = strHatFontBold,
        fontSize = 12.sp,
        lineHeight = 14.sp
    ),
    body4_m_12 = TextStyle(
        fontFamily = strHatFontMedium,
        fontSize = 12.sp,
        lineHeight = 14.sp
    ),
    body4_r_12 = TextStyle(
        fontFamily = strHatFontRegular,
        fontSize = 12.sp,
        lineHeight = 14.sp
    ),
    body5_b_10 = TextStyle(
        fontFamily = strHatFontExtraBold,
        fontSize = 10.sp,
        lineHeight = 12.sp
    ),
)

val LocalStrHatTypographyProvider = staticCompositionLocalOf { defaultStrHatTypography }