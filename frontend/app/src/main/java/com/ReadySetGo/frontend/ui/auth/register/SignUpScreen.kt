package com.ReadySetGo.frontend.ui.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ReadySetGo.frontend.ui.auth.register.SignUpViewModel


/**
 * Ekran Rejestracji — łączy [SignUpViewModel] z [SignUpView].
 * Zbiera stan z ViewModelu i przekazuje go do widoku jako parametry.
 *
 * @param viewModel         ViewModel wstrzykiwany przez Hilt, zarządza stanem rejestracji.
 * @param onNavigateBack    Callback przekierowujący z powrotem do ekranu logowania.
 * @param onRegisterSuccess Callback wywoływany po pomyślnej rejestracji.
 */
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    SignUpView(
        email = state.email,
        onEmailChange = viewModel::onEmailChange,
        password = state.password,
        onPasswordChange = viewModel::onPasswordChange,
        confirmPassword = state.confirmPassword,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        isLoading = state.isLoading,
        error = state.error,
        passwordMismatch = state.passwordMismatch,
        onSignUpClick = { viewModel.signUp(onRegisterSuccess) },
        onNavigateToSignIn = onNavigateBack
    )
}