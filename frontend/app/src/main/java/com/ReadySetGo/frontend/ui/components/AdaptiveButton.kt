package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ReadySetGo.frontend.ui.theme.DarkNavy
import com.ReadySetGo.frontend.ui.theme.White

/**
 * Adaptacyjny przycisk akcji aplikacji.
 * Wyświetla tekst lub wskaźnik ładowania gdy [isLoading] jest true.
 *
 * W przeciwieństwie do PrimaryButton, nie narzuca szerokości (brak fillMaxWidth),
 * dzięki czemu dopasowuje się do przestrzeni nadanej przez modifier.
 *
 * @param text      Tekst wyświetlany na przycisku.
 * @param onClick   Callback wywoływany po kliknięciu.
 * @param modifier  Modifier do kontroli rozmiaru (np. weight, width).
 * @param enabled   Czy aktywny.
 * @param isLoading Czy pokazać loader.
 */
@Composable
fun AdaptiveButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    background: Brush = SolidColor(DarkNavy)
) {
    val shape = RoundedCornerShape(percent = 35)

    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        enabled = enabled && !isLoading,
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background, shape),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = White,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = White
                )
            }
        }
    }
}