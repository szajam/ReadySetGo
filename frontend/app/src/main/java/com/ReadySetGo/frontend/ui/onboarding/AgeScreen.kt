package com.ReadySetGo.frontend.ui.onboarding

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.ui.tooling.preview.Preview
import com.ReadySetGo.frontend.ui.components.OnboardingScaffold
import com.ReadySetGo.frontend.ui.components.VerticalNumberPicker

/**
 * Ekran onboardingu służący do wyboru wieku użytkownika.
 * Komponent wykorzystuje [OnboardingScaffold] jako bazowy układ oraz [VerticalNumberPicker]
 * jako główny element interakcyjny. Wybór wieku jest kluczowy dla personalizacji
 * planów i aktywności treningowych w dalszej części aplikacji.
 *
 * @param onContinue Callback przekazujący wybrany wiek jako Int.
 */
@Composable
fun AgeScreen(
    onContinue: (Int) -> Unit
) {
    /** * Stan przechowujący aktualnie wybraną wartość wieku.
     * Domyślnie ustawiony na 32 lata zgodnie z wymaganiami projektowymi.
     */
    var selectedAge by remember { mutableIntStateOf(32) }

    OnboardingScaffold(
        title = "How Old Are You?",
        description = "Age in years. This will help us to personalize an exercise program that suits you.",
        onContinueClick = { onContinue(selectedAge) }
    ) {
        /**
         * Uniwersalny selektor pionowy.
         * * [range] Definiuje zakres wieku od 18 do 99 lat.
         * [initialValue] Ustawia focus na wartości 32 przy pierwszym uruchomieniu.
         * [modifier] Wymusza wysokość 420.dp (7 elementów * 60.dp), co gwarantuje
         * zachowanie symetrii układu 1:1 (3 liczby nad i 3 pod wybraną wartością).
         */
        VerticalNumberPicker(
            range = (18..99).toList(),
            initialValue = 32,
            onValueChange = { selectedAge = it },
            // USTAW PRECYZYJNĄ WYSOKOŚĆ TUTAJ:
            modifier = Modifier.height(420.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AgeScreenPreview() {
    AgeScreen(onContinue = {})
}