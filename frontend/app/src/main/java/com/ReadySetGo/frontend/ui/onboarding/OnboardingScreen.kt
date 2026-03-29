package com.ReadySetGo.frontend.ui.onboarding

import androidx.compose.runtime.Composable

/**
 * Ekran On Board'ingu — punkt wejścia dla nawigacji.
 * Łączy nawigację z widokiem [OnboardingView].
 *
 * @param onNavigateToSignIn Callback wywoływany po kliknięciu przycisku "Get Started",
 * przekierowuje do ekranu logowania.
 */
@Composable
fun OnboardingScreen(onNavigateToSignIn: () -> Unit) {
    OnboardingView(onGetStarted = onNavigateToSignIn)
}