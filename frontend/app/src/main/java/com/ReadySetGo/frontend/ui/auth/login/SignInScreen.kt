package com.ReadySetGo.frontend.ui.auth.login


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import com.ReadySetGo.frontend.ui.auth.login.SignInViewModel

@Composable
fun SignInScreen(
    // viewModel: SignInViewModel,
    onNavigateToSignUp: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "STUB: Sign In Screen\n(Formularz logowania)")

        // TODO: Użyć AuthTextField, PrimaryButton, SocialLoginBar
    }
}