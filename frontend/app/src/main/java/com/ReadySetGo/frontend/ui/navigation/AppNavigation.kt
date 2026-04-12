package com.ReadySetGo.frontend.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ReadySetGo.frontend.ui.auth.login.SignInScreen
import com.ReadySetGo.frontend.ui.auth.register.SignUpScreen
import com.ReadySetGo.frontend.ui.onboarding.OnboardingScreen

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object SignIn : Screen("sign_in")
    object SignUp : Screen("sign_up")
}

/**
 * Główna funkcja odpowiedzialna za konfigurację nawigacji w aplikacji.
 *
 * Tworzy i zarządza [NavHost], który definiuje dostępne ekrany oraz
 * przejścia między nimi przy użyciu [NavController].
 *
 * Startowym ekranem aplikacji jest ekran onboarding ([Screen.Onboarding]).
 *
 * Zdefiniowane ścieżki nawigacji:
 * - Onboarding → przejście do ekranu logowania (SignIn)
 * - SignIn → przejście do rejestracji (SignUp) lub logowanie użytkownika
 * - SignUp → powrót do poprzedniego ekranu lub rejestracja użytkownika
 *
 * Callbacki:
 * - onNavigateToSignIn:                    przejście z onboarding do logowania
 * - onNavigateToSignUp:                    przejście z logowania do rejestracji
 * - onNavigateBack:                        powrót do poprzedniego ekranu
 * - onLoginSuccess / onRegisterSuccess:    miejsca do obsługi sukcesu (np. przejście do ekranu głównego)
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onNavigateToSignIn = {
                    navController.navigate(Screen.SignIn.route)
                }
            )
        }
        composable(Screen.SignIn.route) {
            SignInScreen(
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
                onLoginSuccess = {
                    // TODO: navigate to home
                }
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    // TODO: navigate to home
                }
            )
        }
    }
}