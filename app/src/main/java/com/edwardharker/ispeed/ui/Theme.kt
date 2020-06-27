package com.edwardharker.ispeed.ui

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

private val DarkColorPalette = darkColorPalette(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary
)

private val LightColorPalette = lightColorPalette(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary
)

@Composable
fun ISpeedTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}