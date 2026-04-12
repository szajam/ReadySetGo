package com.ReadySetGo.frontend.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


/**
 * Ekran Logowania — łączy [SignInViewModel] z [SignInView].
 * Zbiera stan z ViewModelu i przekazuje go do widoku jako parametry.
 *
 * @param viewModel             ViewModel wstrzykiwany przez Hilt, zarządza stanem logowania.
 * @param onNavigateToSignUp    Callback przekierowujący do ekranu rejestracji.
 * @param onLoginSuccess        Callback wywoływany po pomyślnym zalogowaniu.
 */
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onNavigateToSignUp: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    SignInView(
        email = state.email,
        onEmailChange = viewModel::onEmailChange,
        password = state.password,
        onPasswordChange = viewModel::onPasswordChange,
        isLoading = state.isLoading,
        error = state.error,
        onSignInClick = { viewModel.signIn(onLoginSuccess) },
        onNavigateToSignUp = onNavigateToSignUp
    )
}