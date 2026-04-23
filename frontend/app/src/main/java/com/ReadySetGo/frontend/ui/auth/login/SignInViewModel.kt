package com.ReadySetGo.frontend.ui.auth.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


/**
 * Stan UI ekranu Logowania.
 *
 * @property email      Aktualna wartość pola email.
 * @property password   Aktualna wartość pola hasła.
 * @property isLoading  Czy trwa proces logowania.
 * @property error      Komunikat błędu, lub null jeśli brak błędu.
 */
data class SignInState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * ViewModel ekranu Logowania.
 * Zarządza stanem UI i będzie obsługiwał wywołania repozytorium.
 */
@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SignInState())

    /** Publiczny stan UI obserwowany przez [SignInScreen]. */
    val state: StateFlow<SignInState> = _state.asStateFlow()

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
     * Inicjuje proces logowania.
     * TODO: Wywołać repozytorium i obsłużyć odpowiedź.
     *
     * @param onSuccess Callback wywoływany po pomyślnym zalogowaniu.
     */
    fun signIn(onSuccess: () -> Unit) {
        // TODO: call repository
    }
}