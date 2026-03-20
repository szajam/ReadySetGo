package com.ReadySetGo.frontend.ui.auth.onboarding // POPRAW PAKIET NA WŁAŚCIWY

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun OnboardingScreen(
    onNavigateToSignIn: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "STUB: Onboarding Screen\n(Zdjęcie biegaczy + Get Started)")

        // TODO: Dodać PrimaryButton, który wywołuje onNavigateToSignIn()
    }
}