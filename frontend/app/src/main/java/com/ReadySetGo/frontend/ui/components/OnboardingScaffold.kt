package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*

/**
 * Główny szablon (Scaffold) dla ekranów procesu rejestracji i onboardingu.
 * Komponent zapewnia spójny układ wizualny dla wszystkich kroków,
 * automatycznie centrując nagłówek, opis oraz dynamiczną treść.
 *
 * @param title Nagłówek ekranu.
 * @param description Opis ekranu.
 * @param onContinueClick Akcja wykonywana po naciśnięciu przycisku "Continue".
 * @param content Treść ekranu.
 */
@Composable
fun OnboardingScaffold(
    title: String,
    description: String,
    onContinueClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 40.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /**
         * Sekcja nagłówka: Tytuł o pogrubionej czcionce 30.sp.
         */
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            textAlign = TextAlign.Center
        )

        /**
         * Sekcja opisu: Tekst pomocniczy w kolorze szarym (Gray).
         */
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
        )

        /**
         * Kontener na treść ekranu.
         * Użycie [Modifier.weight(1f)] sprawia, że ta sekcja "wypycha" nagłówek do góry
         * i przyciski do dołu, centrując zawartość pionowo dzięki [Arrangement.Center].
         */
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content,
            verticalArrangement = Arrangement.Center
        )

        /**
         * TU PRZYCISK!!
         *
         */

        // TODO: Dodać PrimaryButton, który wywołuje onContinueClick()
        /*
        PrimaryButton(
            text = "Continue",
            onClick = onContinueClick,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        */
    }
}