package com.ReadySetGo.frontend.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ReadySetGo.frontend.ui.components.OnboardingScaffold

/**
 * Ekran wyboru poziomu aktywności fizycznej użytkownika.
 * Pozwala na wybór dokładnie jednej opcji spośród dostępnych poziomów:
 * Beginner, Intermediate, Advanced. Wybór jest odzwierciedlony wizualnie
 * poprzez zmianę tła kafelka na pomarańczowy i koloru tekstu na biały.
 * @param onContinue Callback przekazujący wybrany poziom aktywności jako String.
 */
@Composable
fun ActivityLevelScreen(
    onContinue: (String) -> Unit
) {
    val levels = listOf("Beginner", "Intermediate", "Advanced")
    var selectedLevel by remember { mutableStateOf("Intermediate") }

    OnboardingScaffold(
        title = "Physical Activity Level?",
        description = "Choose your regular activity level. This will help us to personalize feed for you.",
        onContinueClick = { onContinue(selectedLevel) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            levels.forEach { level ->
                ActivityLevelItem(
                    label = level,
                    isSelected = level == selectedLevel,
                    onClick = { selectedLevel = level }
                )
            }
        }
    }
}

/**
 * Komponent pojedynczego kafelka wyboru poziomu aktywności.
 * * @param label Tekst wyświetlany na kafelku.
 * @param isSelected Czy kafelek jest aktualnie zaznaczony.
 * @param onClick Akcja wykonywana po kliknięciu w kafelek.
 */
@Composable
fun ActivityLevelItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFFFF7A00) else Color.Transparent
    val borderColor = if (isSelected) Color(0xFFFF7A00) else Color(0xFFDCC7B9)
    val textColor = if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityLevelScreenPreview() {
    ActivityLevelScreen(onContinue = {})
}