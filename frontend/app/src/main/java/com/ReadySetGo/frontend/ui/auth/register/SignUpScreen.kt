package com.ReadySetGo.frontend.ui.auth.register


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
// import com.ReadySetGo.frontend.ui.auth.register.SignUpViewModel

@Composable
fun SignUpScreen(
    // viewModel: SignUpViewModel, // Hilt wstrzyknie to później
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "STUB: Sign Up Screen\n(Formularz rejestracji + Walidacja haseł)")

        // TODO: Użyć AuthTextField, ValidationErrorMessage, PrimaryButton
    }
}