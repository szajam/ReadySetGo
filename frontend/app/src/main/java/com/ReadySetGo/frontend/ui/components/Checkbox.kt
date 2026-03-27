package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class GoalOption(
    val title: String
)

/**
 *Komponent wyświetlający pola do zanaczania
 */
@Composable
fun GoalSelector(
    options: List<GoalOption>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        options.forEachIndexed { index, option ->
            GoalItem(
                text = option.title,
                isSelected = index == selectedIndex,
                onClick = { onOptionSelected(index) }
            )
        }
    }
}

/**
 * Wewnętrzny komponent dla pola do zaznaczania.
 * @param text Etykieta
 * @param isSelected Zmienna logiczna dla pola do zaznaczania (default: false)
 * @param onClick Akcja kliknięcia
 */

@Composable
fun GoalItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFFFF7A00) else Color.LightGray
    val backgroundColor = if (isSelected) Color(0xFFFFE6D5) else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)

        Box(
            modifier = Modifier
                .size(18.dp)
                .border(2.dp, borderColor, CircleShape)
                .background(
                    if (isSelected) Color(0xFFFF7A00) else Color.Transparent,
                    CircleShape
                )
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GoalSelectorPreview() {
    var selectedIndex by remember { mutableStateOf(0) }

    val options = listOf(
        GoalOption("Get Fitter"),
        GoalOption("Gain Weight"),
        GoalOption("Lose Weight"),
        GoalOption("Build Muscles"),
        GoalOption("Nie wiem co"),
        GoalOption("Others")
    )

    GoalSelector(
        options = options,
        selectedIndex = selectedIndex,
        onOptionSelected = { selectedIndex = it }
    )
}