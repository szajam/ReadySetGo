package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ReadySetGo.frontend.ui.theme.DarkNavy
import com.ReadySetGo.frontend.ui.theme.White

/**
 * Główny przycisk akcji aplikacji.
 * Wyświetla tekst ze strzałką lub wskaźnik ładowania gdy [isLoading] jest true.
 * Używany na ekranach onboardingu, logowania i rejestracji.
 *
 * @param text      Tekst wyświetlany na przycisku (np. "Sign In", "Sign Up", "Get Started").
 * @param onClick   Callback wywoływany po kliknięciu przycisku.
 * @param modifier  Opcjonalny modifier dla dostosowania wyglądu.
 * @param enabled   Czy przycisk jest aktywny — domyślnie true.
 * @param isLoading Czy wyświetlać wskaźnik ładowania zamiast tekstu — domyślnie false.
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(containerColor = DarkNavy),
        enabled = enabled && !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = White,
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = "$text →",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = White
            )
        }
    }
}