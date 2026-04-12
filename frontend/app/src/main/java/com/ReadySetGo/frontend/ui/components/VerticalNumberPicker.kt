package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlin.math.abs

/**
 * Wertykalny selektor liczbowy z dynamicznym skalowaniem.
 * Komponent został zaprojektowany tak, aby wyświetlać dokładnie 7 elementów w widoku.
 * Środkowy element (wybrany) jest wyróżniony kolorem pomarańczowym i ograniczony liniami
 * poziomu (HorizontalDivider). Pozostałe elementy płynnie maleją i tracą na przezroczystości
 * wraz z oddalaniem się od centrum.
 *
 * @param range Zakres liczb do wyboru
 * @param initialValue Początkowa wartość
 * @param onValueChange Funkcja wywoływana przy każdym zniszczeniu wybranej wartości
 * @param modifier Modyfikator
 */
@OptIn(ExperimentalSnapperApi::class)
@Composable
fun VerticalNumberPicker(
    range: List<Int>,
    initialValue: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    /** Wysokość pojedyńczego wiersza w DP. */
    val itemHeight = 60.dp

    val initialIndex = range.indexOf(initialValue).coerceAtLeast(0)

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)

    /** Obsługa zachowania "przyciągania" do najbliższego elementu przy puszczeniu przewijania. */
    val snapperFlingBehavior = rememberSnapperFlingBehavior(listState)

    /** * Obliczanie indeksu elementu znajdującego się najbliżej geometrycznego środka kontenera.
     * Wykorzystuje stan listy w czasie rzeczywistym.
     */
    val selectedIndex = remember(listState) {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) initialIndex
            else {
                val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
                visibleItems.minByOrNull { abs((it.offset + it.size / 2) - viewportCenter) }?.index ?: initialIndex
            }
        }
    }

    /** Reagowanie na zmianę wybranego indeksu i powiadamianie nadrzędnego komponentu. */
    LaunchedEffect(selectedIndex.value) {
        onValueChange(range[selectedIndex.value])
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        LazyColumn(
            state = listState,
            flingBehavior = snapperFlingBehavior,
            /** * Padding pionowy równy 3 wysokościom elementów (itemHeight * 3).
             * Gwarantuje, że 4. element (środkowy) znajdzie się idealnie w centrum widoku 7-elementowego.
             */
            contentPadding = PaddingValues(vertical = itemHeight * 3),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(itemHeight * 7) // WYMUSZAMY wysokość na 7 elementów
        ) {
            itemsIndexed(range) { index, value ->
                val distance = abs(index - selectedIndex.value)

                /** * Mapa progresu wielkości i przezroczystości (Efekt 1:1):
                 * 0 (wybrany) -> 1.0 (pełna skala, brak przezroczystości)
                 * 1 (sąsiad bezpośredni) -> 0.7
                 * 2 (kolejny sąsiad) -> 0.45
                 * 3 (krawędź widoczności) -> 0.25
                 */
                val progress = when (distance) {
                    0 -> 1.0f
                    1 -> 0.7f
                    2 -> 0.45f
                    3 -> 0.25f
                    else -> 0.1f
                }

                val fontSize = lerpSp(26.sp, 46.sp, progress)
                val alpha = lerpFloat(0.15f, 1f, progress)
                val isSelected = distance == 0

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (isSelected) {
                        HorizontalDivider(
                            color = Color(0xFFFF6D00),
                            thickness = 2.dp,
                            modifier = Modifier.width(65.dp)
                        )
                    }

                    Text(
                        text = value.toString(),
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = fontSize,
                            color = (if (isSelected) Color(0xFFFF6D00) else Color.Black).copy(alpha = alpha)
                        )
                    )

                    if (isSelected) {
                        HorizontalDivider(
                            color = Color(0xFFFF6D00),
                            thickness = 2.dp,
                            modifier = Modifier.width(65.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Liniowa interpolacja dla jednostek wielkości czcionki (TextUnit).
 */
private fun lerpSp(start: TextUnit, end: TextUnit, fraction: Float): TextUnit {
    return (start.value + (end.value - start.value) * fraction).sp
}

/**
 * Liniowa interpolacja dla wartości zmiennoprzecinkowych (Float).
 */
private fun lerpFloat(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}