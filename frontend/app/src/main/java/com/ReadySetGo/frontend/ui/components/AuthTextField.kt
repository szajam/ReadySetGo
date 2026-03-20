package com.ReadySetGo.frontend.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Reużywalne pole tekstowe dla logowania/rejestracji (Email, Hasło).
 */
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    errorMessage: String? = null // Jeśli nie null, pokazuje błąd (np. czerwony border)
) {
    // TODO: Zaimplementować wygląd: Szare tło, ikona po lewej, obsługa hasła (oko)
}
