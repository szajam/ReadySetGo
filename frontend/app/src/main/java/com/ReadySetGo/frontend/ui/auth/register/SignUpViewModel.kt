package com.ReadySetGo.frontend.ui.auth.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Stan UI ekranu Rejestracji.
 *
 * @property email              Aktualna wartość pola email.
 * @property password           Aktualna wartość pola hasła.
 * @property confirmPassword    Aktualna wartość pola potwierdzenia hasła.
 * @property isLoading          Czy trwa proces rejestracji.
 * @property error              Komunikat błędu, lub null jeśli brak błędu.
 * @property passwordMismatch   Zwraca true jeśli hasła nie są zgodne.
 */
data class SignUpState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val passwordMismatch: Boolean
        get() = confirmPassword.isNotEmpty() && password != confirmPassword
}

/**
 * ViewModel ekranu rejestracji.
 * Zarządza stanem UI i będzie obsługiwał wywołania repozytorium.
 */
@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())

    /** Publiczny stan UI obserwowany przez [SignUpScreen]. */
    val state: StateFlow<SignUpState> = _state.asStateFlow()

    /**
     * Aktualizuje pole email w stanie UI.
     *
     * @param email Nowa wartość pola email.
     */
    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    /**
     * Aktualizuje pole hasła w stanie UI.
     *
     * @param password Nowa wartość pola hasła.
     */
    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    /**
     * Aktualizuje pole potwierdzenia hasła w stanie UI.
     *
     * @param confirmPassword Nowa wartość pola potwierdzenia hasła.
     */
    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.value = _state.value.copy(confirmPassword = confirmPassword)
    }

    /**
     * Inicjuje proces rejestracji.
     * TODO: Wywołać repozytorium i obsłużyć odpowiedź.
     *
     * @param onSuccess Callback wywoływany po pomyślnej rejestracji.
     */
    fun signUp(onSuccess: () -> Unit) {
        // TODO: call repository
    }
}