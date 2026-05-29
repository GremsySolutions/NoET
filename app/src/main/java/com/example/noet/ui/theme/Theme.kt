package com.example.noet.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = NoETColor.primaryColorDark,
    onPrimary = NoETColor.onprimaryColorDark,
    primaryContainer = NoETColor.primaryContainerColorDark,
    onPrimaryContainer = NoETColor.onprimaryContainerColorDark,

    secondary = NoETColor.secondaryColorDark,
    onSecondary = NoETColor.onsecondaryColorDark,
    secondaryContainer = NoETColor.secondaryContainerColorDark,
    onSecondaryContainer = NoETColor.onsecondaryContainerColorDark,

    tertiary = NoETColor.tertiaryColorDark,
    onTertiary = NoETColor.ontertiaryColorDark,
    tertiaryContainer = NoETColor.tertiaryContainerColorDark,
    onTertiaryContainer = NoETColor.ontertiaryContainerColorDark,

    error = NoETColor.errorColorDark,
    onError = NoETColor.onerrorColorDark,
    errorContainer = NoETColor.errorContainerColorDark,
    onErrorContainer = NoETColor.onerrorContainerColorDark,

    surfaceDim = NoETColor.surfaceDimColorDark,
    surface = NoETColor.surfaceColorDark,
    surfaceBright = NoETColor.surfaceBrightColorDark,

    surfaceContainerLow = NoETColor.surfaceContainerLowColorDark,
    surfaceContainerLowest = NoETColor.surfaceContainerLowestColorDark,
    surfaceContainer = NoETColor.surfaceContainerColorDark,
    surfaceContainerHigh = NoETColor.surfaceContainerHighColorDark,
    surfaceContainerHighest = NoETColor.surfaceContainerHighestColorDark,

    onSurface = NoETColor.onsurfaceColorDark,
    onSurfaceVariant = NoETColor.onsurfaceVariantColorDark,

    outline = NoETColor.outlineColorDark,
    outlineVariant = NoETColor.outlineVariantColorDark,

    inverseOnSurface = NoETColor.inverseonSurfaceColorDark,
    inverseSurface = NoETColor.inverseSurfaceColorDark,
    inversePrimary = NoETColor.inverseprimaryColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = NoETColor.primaryColorLight,
    onPrimary = NoETColor.onprimaryColorLight,
    primaryContainer = NoETColor.primaryContainerColorLight,
    onPrimaryContainer = NoETColor.onprimaryContainerColorLight,

    secondary = NoETColor.secondaryColorLight,
    onSecondary = NoETColor.onsecondaryColorLight,
    secondaryContainer = NoETColor.secondaryContainerColorLight,
    onSecondaryContainer = NoETColor.onsecondaryContainerColorLight,

    tertiary = NoETColor.tertiaryColorLight,
    onTertiary = NoETColor.ontertiaryColorLight,
    tertiaryContainer = NoETColor.tertiaryContainerColorLight,
    onTertiaryContainer = NoETColor.ontertiaryContainerColorLight,

    error = NoETColor.errorColorLight,
    onError = NoETColor.onerrorColorLight,
    errorContainer = NoETColor.errorContainerColorLight,
    onErrorContainer = NoETColor.onerrorContainerColorLight,

    surfaceDim = NoETColor.surfaceDimColorLight,
    surface = NoETColor.surfaceColorLight,
    surfaceBright = NoETColor.surfaceBrightColorLight,

    surfaceContainerLow = NoETColor.surfaceContainerLowColorLight,
    surfaceContainerLowest = NoETColor.surfaceContainerLowestColorLight,
    surfaceContainer = NoETColor.surfaceContainerColorLight,
    surfaceContainerHigh = NoETColor.surfaceContainerHighColorLight,
    surfaceContainerHighest = NoETColor.surfaceContainerHighestColorLight,

    onSurface = NoETColor.onsurfaceColorLight,
    onSurfaceVariant = NoETColor.onsurfaceVariantColorLight,

    outline = NoETColor.outlineColorLight,
    outlineVariant = NoETColor.outlineVariantColorLight,

    inverseOnSurface = NoETColor.inverseonSurfaceColorLight,
    inverseSurface = NoETColor.inverseSurfaceColorLight,
    inversePrimary = NoETColor.inverseprimaryColorLight
)

@Composable
fun NoETTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}