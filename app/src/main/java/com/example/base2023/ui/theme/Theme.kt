package com.example.base2023.ui.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.base2023.R
import com.example.base2023.util.Constants.TEXT_10SP_LINE_HEIGHT
import com.example.base2023.util.Constants.TEXT_12SP_LINE_HEIGHT
import com.example.base2023.util.Constants.TEXT_14SP_LINE_HEIGHT
import com.example.base2023.util.Constants.TEXT_16SP_LINE_HEIGHT
import com.example.base2023.util.Constants.TEXT_17SP_LINE_HEIGHT
import com.example.base2023.util.Constants.TEXT_18SP_LINE_HEIGHT
import com.example.base2023.util.Constants.TEXT_24SP_LINE_HEIGHT
import com.example.base2023.util.Constants.TEXT_32SP_LINE_HEIGHT


val InterFont = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_mediun, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

@Immutable
data class CustomColors(
    val darkTheme: Boolean,
    val background: Color,
    val backgroundBottomSheet: Color,
    val backgroundDim: Color,
    val elevated: Color,
    val toastBackGround: Color,
    val editable: Color,
    val primary: Color,
    val inactive: Color,
    val textPrimary: Color,
    val textDescription: Color,
    val textButtonPrimary: Color,
    val textPlaceHolder: Color,
    val error: Color,
    val textFieldIconTint: Color,
    val iconTint: Color,
)

@Immutable
data class CustomTypography(
    val bold18: TextStyle,
    val bold16: TextStyle,
    val bold14: TextStyle,
    val bold12: TextStyle,
    val semiBold17: TextStyle,
    val medium32: TextStyle,
    val medium24: TextStyle,
    val medium18: TextStyle,
    val medium16: TextStyle,
    val medium14: TextStyle,
    val medium12: TextStyle,
    val medium10: TextStyle,
    val regular18: TextStyle,
    val regular16: TextStyle,
    val regular14: TextStyle,
    val regular12: TextStyle,
    val caption: TextStyle,
    val description14: TextStyle,
    val description12: TextStyle,
    val buttonPrimary: TextStyle,
    val buttonSecondary: TextStyle,
    val clickable: TextStyle,
    val placeHolder: TextStyle,
    val error: TextStyle,
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        darkTheme = false,
        background = Color.Unspecified,
        backgroundBottomSheet = Color.Unspecified,
        backgroundDim = Color.Unspecified,
        elevated = Color.Unspecified,
        toastBackGround = Color.Unspecified,
        editable = Color.Unspecified,
        primary = Color.Unspecified,
        inactive = Color.Unspecified,
        textPrimary = Color.Unspecified,
        textDescription = Color.Unspecified,
        textButtonPrimary = Color.Unspecified,
        textPlaceHolder = Color.Unspecified,
        error = Color.Unspecified,
        textFieldIconTint = Color.Unspecified,
        iconTint = Color.Unspecified,
    )
}
val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        bold18 = TextStyle.Default,
        bold16 = TextStyle.Default,
        bold14 = TextStyle.Default,
        bold12 = TextStyle.Default,
        semiBold17 = TextStyle.Default,
        medium32 = TextStyle.Default,
        medium24 = TextStyle.Default,
        medium18 = TextStyle.Default,
        medium16 = TextStyle.Default,
        medium14 = TextStyle.Default,
        medium12 = TextStyle.Default,
        medium10 = TextStyle.Default,
        regular18 = TextStyle.Default,
        regular16 = TextStyle.Default,
        regular14 = TextStyle.Default,
        regular12 = TextStyle.Default,
        caption = TextStyle.Default,
        description14 = TextStyle.Default,
        description12 = TextStyle.Default,
        buttonPrimary = TextStyle.Default,
        buttonSecondary = TextStyle.Default,
        clickable = TextStyle.Default,
        placeHolder = TextStyle.Default,
        error = TextStyle.Default,
    )
}

@Composable
fun CustomTheme(
    darkTheme: Boolean = false, content: @Composable () -> Unit
) {
    val customColors = CustomColors(
        darkTheme = darkTheme,
        background = if (darkTheme) Black12 else WhiteFC,
        backgroundBottomSheet = if (darkTheme) Black36 else WhiteFC,
        backgroundDim = if (darkTheme) Black0040 else Black0020,
        elevated = if (darkTheme) WhiteFC16 else WhiteEA40,
        toastBackGround = if (darkTheme) Black1E else WhiteFC,
        editable = if (darkTheme) Black2B else WhiteF6,
        primary = if (darkTheme) PrimaryDark else PrimaryLight,
        inactive = if (darkTheme) WhiteFC24 else GrayAC24,
        textPrimary = if (darkTheme) WhiteFC else Black1E,
        textDescription = if (darkTheme) WhiteFC64 else Black1E80,
        textButtonPrimary = if (darkTheme) WhiteFC else WhiteFC,
        textPlaceHolder = if (darkTheme) WhiteFC40 else GrayAC,
        error = if (darkTheme) ErrorDark else ErrorLight,
        textFieldIconTint = if (darkTheme) WhiteFC else Black1E,
        iconTint = if (darkTheme) WhiteFC40 else GrayAC,
    )
    val defaultTextStyle = TextStyle(
        fontFamily = InterFont, color = customColors.textPrimary
    )
    val customTypography = CustomTypography(
        bold18 = defaultTextStyle.copy(
            fontWeight = FontWeight.Bold, fontSize = 18.sp, lineHeight = TEXT_18SP_LINE_HEIGHT.sp
        ),
        bold16 = defaultTextStyle.copy(
            fontWeight = FontWeight.Bold, fontSize = 16.sp, lineHeight = TEXT_16SP_LINE_HEIGHT.sp
        ),
        bold14 = defaultTextStyle.copy(
            fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
        bold12 = defaultTextStyle.copy(
            fontWeight = FontWeight.Bold, fontSize = 12.sp, lineHeight = TEXT_12SP_LINE_HEIGHT.sp
        ),
        semiBold17 = defaultTextStyle.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            lineHeight = TEXT_17SP_LINE_HEIGHT.sp
        ),
        medium32 = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium, fontSize = 32.sp, lineHeight = TEXT_32SP_LINE_HEIGHT.sp
        ),
        medium24 = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium, fontSize = 24.sp, lineHeight = TEXT_24SP_LINE_HEIGHT.sp
        ),
        medium18 = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium, fontSize = 18.sp, lineHeight = TEXT_18SP_LINE_HEIGHT.sp
        ),
        medium16 = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium, fontSize = 16.sp, lineHeight = TEXT_16SP_LINE_HEIGHT.sp
        ),
        medium14 = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
        medium12 = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = TEXT_12SP_LINE_HEIGHT.sp
        ),
        medium10 = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium, fontSize = 10.sp, lineHeight = TEXT_10SP_LINE_HEIGHT.sp
        ),
        regular18 = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal, fontSize = 18.sp, lineHeight = TEXT_18SP_LINE_HEIGHT.sp
        ),
        regular16 = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = TEXT_16SP_LINE_HEIGHT.sp
        ),
        regular14 = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
        regular12 = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = TEXT_12SP_LINE_HEIGHT.sp
        ),
        caption = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = TEXT_12SP_LINE_HEIGHT.sp
        ),
        // -----------------
        description14 = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = customColors.textDescription,
            lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
        description12 = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = customColors.textDescription,
            lineHeight = TEXT_12SP_LINE_HEIGHT.sp
        ),
        buttonPrimary = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = customColors.textButtonPrimary,
            lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
        buttonSecondary = defaultTextStyle.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color.White,
            lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
        clickable = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = customColors.primary,
            lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
        placeHolder = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = customColors.textPlaceHolder,
            lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
        error = defaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = customColors.error,
            lineHeight = TEXT_14SP_LINE_HEIGHT.sp
        ),
    )
    val customRippleTheme = object : RippleTheme {
        @Composable
        override fun defaultColor(): Color = CustomTheme.colors.primary

        @Composable
        override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
            Color.Black, lightTheme = !darkTheme
        )
    }
    CompositionLocalProvider(
        LocalRippleTheme provides customRippleTheme,
        LocalCustomColors provides customColors,
        LocalCustomTypography provides customTypography,
        content = content
    )
}

// Use with eg. CustomTheme.typography.h1
object CustomTheme {
    val colors: CustomColors
        @Composable get() = LocalCustomColors.current
    val typography: CustomTypography
        @Composable get() = LocalCustomTypography.current
}