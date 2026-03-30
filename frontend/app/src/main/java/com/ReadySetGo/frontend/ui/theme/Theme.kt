package com.ReadySetGo.frontend.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ── Brand colors ─────────────────────────────────────────────
val DarkNavy = Color(0xFF192126)
val LimeGreen = Color(0xFFBBF246)
val Orange = Color(0xFFF97316)
val Sand = Color(0xFFC1A188)
val Brown = Color(0xFF544026)

// ── UI colors ─────────────────────────────────────────────────
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF1A1A1A)
val ErrorRed = Color(0xFFE53935)
val ErrorRedLight = Color(0xFFFFEBEE)
val InputGray = Color(0xFFF0F0F0)
val SurfaceGray = Color(0xFFF5F5F5)
val TextGray = Color(0xFF9E9E9E)
val BorderGray = Color(0xFFE0E0E0)

private val LightColorScheme = lightColorScheme(
    primary = DarkNavy,
    onPrimary = White,
    secondary = LimeGreen,
    onSecondary = DarkNavy,
    tertiary = Orange,
    onTertiary = White,
    background = White,
    onBackground = DarkNavy,
    surface = InputGray,
    onSurface = DarkNavy,
    error = ErrorRed,
    onError = White
)

@Composable
fun ReadySetGoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}