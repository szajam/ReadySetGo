package com.ReadySetGo.frontend.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ReadySetGo.frontend.ui.components.*

/**
 * Ekran wyboru celów treningowych użytkownika.
 * * Ekran ten pozwala na wybór wielu opcji jednocześnie
 * @param onContinue Funkcja wywoływana po zakończeniu wyboru. Przekazuje listę wybranych opcji.
 */
@Composable
fun GoalScreen(
    onContinue: (List<String>) -> Unit
) {
    // Lista statyczna do zminy
    val goalOptions = listOf(
        GoalOption("Get Fitter"),
        GoalOption("Gain Weight"),
        GoalOption("Lose Weight"),
        GoalOption("Build Muscles"),
        GoalOption("Nie wiem co"),
        GoalOption("Others")
    )

    /**
     * Stan przechowujący unikalne indeksy zaznaczonych opcji.
     * Użycie [Set] ułatwia logikę dodawania i usuwania elementów (toggle).
     */
    var selectedIndices by remember { mutableStateOf(setOf<Int>()) }

    OnboardingScaffold(
        title = "What is Your Goal?",
        description = "You can choose more than one. Don't worry, you can always change it later.",
        onContinueClick = {
            // Przekazujemy listę tytułów wybranych opcji
            val selectedGoals = selectedIndices.map { goalOptions[it].title }
            onContinue(selectedGoals)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            /**
             * Wykorzystanie komponentu [GoalItem] do wyświetlenia pojedynczej opcji.
             * Logika onClick obsługuje przełączanie stanu (zaznacz/odznacz).
             */
            goalOptions.forEachIndexed { index, option ->
                GoalItem(
                    text = option.title,
                    isSelected = selectedIndices.contains(index),
                    onClick = {
                        // Logika przełączania (toggle): jeśli jest, usuń; jeśli nie ma, dodaj.
                        selectedIndices = if (selectedIndices.contains(index)) {
                            selectedIndices - index
                        } else {
                            selectedIndices + index
                        }
                    }
                )
                // Odstęp między kafelkami
                if (index < goalOptions.lastIndex) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

/**
 * Podgląd ekranu
 */
@Preview(showBackground = true)
@Composable
fun GoalScreenPreview() {
    GoalScreen(onContinue = {})
}