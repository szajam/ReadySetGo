package com.ReadySetGo.frontend.ui.onboarding

import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ReadySetGo.frontend.ui.components.HorizontalNumberPicker
import com.ReadySetGo.frontend.ui.components.OnboardingScaffold

/**
 * Ekran procesu onboardingu służący do wyboru wagi użytkownika (w kg).
 *
 * Komponent wykorzystuje [HorizontalNumberPicker] do prezentacji wag w formie
 * poziomego suwaka. Układ jest osadzony w [OnboardingScaffold], co zapewnia
 * spójność z pozostałymi ekranami rejestracji.
 * @param onContinue Callback wywoływany po kliknięciu przycisku kontynuacji.
 * Przekazuje wybraną wartość wagi (Int) do dalszych etapów onboardingu.
 */
@Composable
fun WeightScreen(
    onContinue: (Int) -> Unit
) {
    var selectedWeight by remember { mutableIntStateOf(50) }

    OnboardingScaffold(
        title = "What is Your Weight?",
        description = "Weight in kg. Don't worry, you can always change it later.",
        onContinueClick = { onContinue(selectedWeight) }
    ) {
        /**
         * Kontener środkowy pozycjonujący selektor wagi.
         * Zastosowano pionowy padding (40.dp), aby zachować odpowiedni dystans
         * między opisem a suwakiem zgodnie z makietą.
         */
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp),
            verticalArrangement = Arrangement.Center
        ) {
            /**
             * Poziomy selektor liczb.
             * @param range Zakres dostępnych wag: od 30 do 150 kg.
             * @param initialValue Wartość startowa (focus) ustawiona na 32.
             */
            HorizontalNumberPicker(
                range = (30..150).toList(),
                initialValue = 32,
                onValueChange = { selectedWeight = it }
            )
        }
    }
}


@Preview(showBackground = true, name = "Weight Selection 1:1 Preview")
@Composable
fun WeightScreenPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.ui.graphics.Color.White
        ) {
            WeightScreen(
                onContinue = { weight ->
                    println("Wybrana waga: $weight kg")
                }
            )
        }
    }
}