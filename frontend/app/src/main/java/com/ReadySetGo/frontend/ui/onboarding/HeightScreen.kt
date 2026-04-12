package com.ReadySetGo.frontend.ui.onboarding

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ReadySetGo.frontend.ui.components.OnboardingScaffold
import com.ReadySetGo.frontend.ui.components.VerticalNumberPicker

/**
 * Ekran procesu onboardingu służący do wyboru wzrostu użytkownika (w cm).
 *
 * Komponent wykorzystuje pionowy selektor [VerticalNumberPicker] z zakresem od 100 do 250 cm.
 * Zgodnie z projektem graficznym, picker jest skonfigurowany na wysokość 420.dp, co pozwala
 * wyświetlić dokładnie 7 wartości (3 powyżej, wybraną oraz 3 poniżej).
 *
 * @param onContinue Callback wywoływany po kliknięciu przycisku kontynuacji,
 * przekazujący wybrany wzrost (Int).
 */
@Composable
fun HeightScreen(
    onContinue: (Int) -> Unit
) {
    /** * Stan przechowujący wybrany wzrost.
     * Wartość początkowa ustawiona na 175 cm zgodnie z projektem.
     */
    var selectedHeight by remember { mutableIntStateOf(175) }

    OnboardingScaffold(
        title = "What is Your Height?",
        description = "Height in cm. Don't worry, you can always change it later.",
        onContinueClick = { onContinue(selectedHeight) }
    ) {
        /**
         * Pionowy selektor wzrostu.
         * Zakres: 100 - 250 cm.
         * Wysokość: 420.dp (7 elementów x 60.dp każdy).
         */
        VerticalNumberPicker(
            range = (100..250).toList(),
            initialValue = 175,
            onValueChange = { selectedHeight = it },
            modifier = Modifier.height(420.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeightScreenPreview() {
    HeightScreen(onContinue = {})
}