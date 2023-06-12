package id.dipoengoro.dcontact.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.dipoengoro.dcontact.R

val MontserratFontFamily = FontFamily(
    listOf(
        Font(R.font.montserrat),
        Font(R.font.montserrat_medium, FontWeight.Medium),
        Font(R.font.montserrat_semibold, FontWeight.SemiBold)
    )
)

private fun textDefault400(
    fontSize: Int,
    lineHeight: Int,
    letterSpacing: Double = .0,
    fontWeight: FontWeight = FontWeight.W400,
): TextStyle =
    TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        lineHeight = lineHeight.sp,
        letterSpacing = letterSpacing.sp
    )

private fun textDefault500(fontSize: Int, lineHeight: Int, letterSpacing: Double = .0): TextStyle =
    textDefault400(fontSize, lineHeight, letterSpacing, fontWeight = FontWeight.W500)

val Typography = Typography(
    displayLarge = textDefault400(fontSize = 57, lineHeight = 64, letterSpacing = -.25),
    displayMedium = textDefault400(fontSize = 45, lineHeight = 52),
    displaySmall = textDefault400(fontSize = 36, lineHeight = 44),
    headlineLarge = textDefault400(fontSize = 32, lineHeight = 40),
    headlineMedium = textDefault400(fontSize = 28, lineHeight = 36),
    headlineSmall = textDefault400(fontSize = 24, lineHeight = 32),
    titleLarge = textDefault400(fontSize = 22, lineHeight = 28),
    titleMedium = textDefault500(fontSize = 16, lineHeight = 24, letterSpacing = .15),
    titleSmall = textDefault500(fontSize = 14, lineHeight = 20, letterSpacing = .1),
    labelLarge = textDefault500(fontSize = 14, lineHeight = 20, letterSpacing = .1),
    labelMedium = textDefault500(fontSize = 12, lineHeight = 16, letterSpacing = .5),
    labelSmall = textDefault500(fontSize = 11, lineHeight = 16, letterSpacing = .5),
    bodyLarge = textDefault400(fontSize = 16, lineHeight = 24, letterSpacing = .5),
    bodyMedium = textDefault400(fontSize = 14, lineHeight = 20, letterSpacing = .25),
    bodySmall = textDefault400(fontSize = 12, lineHeight = 16, letterSpacing = .4)
)