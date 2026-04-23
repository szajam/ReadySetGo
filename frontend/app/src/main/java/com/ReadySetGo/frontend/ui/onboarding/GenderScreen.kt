package com.ReadySetGo.frontend.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ReadySetGo.frontend.R
import com.ReadySetGo.frontend.ui.components.OnboardingScaffold

/**
 * Ekran procesu onboardingu służący do określenia płci użytkownika.
 * * Komponent prezentuje dwie duże, interaktywne sfery (Male i Female). Wybór płci jest
 * niezbędny do kalibracji algorytmów treningowych oraz personalizacji treści feedu.
 * Ekran korzysta z [OnboardingScaffold] jako bazy strukturalnej.
 *
 * @param onNextClick Callback wywoływany po kliknięciu przycisku kontynuacji.
 * Przekazuje wybraną płeć jako [String].
 */
@Composable
fun GenderScreen(
    onNextClick: (String) -> Unit
) {
    /** Stan przechowujący aktualnie zaznaczoną płeć. Domyślnie ustawiony na "Male". */
    var selectedGender by remember { mutableStateOf("Male") }

    OnboardingScaffold(
        title = "Tell Us About Yourself",
        description = "To give you a better experience and results we need to know your gender.",
        onContinueClick = { onNextClick(selectedGender) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /** Selektor dla płci męskiej z charakterystycznym pomarańczowym kolorem aktywnym. */
            GenderCircle(
                label = "Male",
                iconRes = R.drawable.male,
                isSelected = selectedGender == "Male",
                activeColor = Color(0xFFFF6D00),
                onClick = { selectedGender = "Male" }
            )

            Spacer(modifier = Modifier.height(24.dp))

            /** Selektor dla płci żeńskiej z charakterystycznym beżowym kolorem aktywnym. */
            GenderCircle(
                label = "Female",
                iconRes = R.drawable.male,
                isSelected = selectedGender == "Female",
                activeColor = Color(0xFFC4A484),
                onClick = { selectedGender = "Female" }
            )
        }
    }
}

/**
 * Komponent pomocniczy - okrągły przycisk wyboru płci.
 *
 * Komponent dynamicznie zmienia przezroczystość tła (alpha) w zależności od stanu [isSelected].
 * Wykorzystuje [MutableInteractionSource] z brakiem indykacji wizualnej (indication = null),
 * aby zachować czysty wygląd zgodny z projektem graficznym.
 *
 * @param label Etykieta tekstowa wyświetlana pod ikoną.
 * @param iconRes Zasób graficzny ikony.
 * @param isSelected Czy dany element jest aktualnie wybrany.
 * @param activeColor Kolor bazowy sfery, używany w stanie aktywnym (pełny) i nieaktywnym (20% alpha).
 * @param onClick Akcja wykonywana po dotknięciu sfery.
 */
@Composable
private fun GenderCircle(
    label: String,
    iconRes: Int,
    isSelected: Boolean,
    activeColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(218.dp)
            .background(
                color = if (isSelected) activeColor else activeColor.copy(alpha = 0.2f),
                shape = CircleShape
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(72.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = label,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 22.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenderScreenPreview() {
    GenderScreen(onNextClick = {})
}