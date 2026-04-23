package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlin.math.abs

/**
 * Horyzontalny picker liczbowy z dynamicznym skalowanniem. Komponent wyświetla pasmo liczb, w którym środkowy element jest największy i wyróżniony kolorystycznie
 * @param range Zakres liczb do wyboru
 * @param initialValue Początkowa wartość
 * @param onValueChange Funkcja wywoływana przy każdym zniszczeniu wybranej wartości
 * @param modifier Modyfikator
 *
 */
@OptIn(ExperimentalSnapperApi::class)
@Composable
fun HorizontalNumberPicker(
    range: List<Int>,
    initialValue: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val itemWidthDp = 70.dp
    val totalPickerWidth = itemWidthDp * 5
    val initialIndex = range.indexOf(initialValue).coerceAtLeast(0)

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = (initialIndex - 2).coerceAtLeast(0)
    )
    val snapperFlingBehavior = rememberSnapperFlingBehavior(listState)

    // Obliczanie wybranego indeksu (środkowy z 5 widocznych)
    val selectedIndex = remember(listState) {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) initialIndex
            else {
                // Wybieramy element, który jest najbliżej środka naszego 5-elementowego kontenera
                val internalCenter = layoutInfo.viewportStartOffset + (layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset) / 2
                visibleItems.minByOrNull { abs((it.offset + it.size / 2) - internalCenter) }?.index ?: initialIndex
            }
        }
    }

    LaunchedEffect(selectedIndex.value) {
        onValueChange(range[selectedIndex.value])
    }

    // Modifier.fillMaxWidth() i Alignment.Start sprawią, że zacznie się od lewej
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .width(totalPickerWidth) // Sztywna szerokość na 5 elementów
                .height(110.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                state = listState,
                flingBehavior = snapperFlingBehavior,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(range) { index, value ->
                    val distance = abs(index - selectedIndex.value)

                    /**
                     * postęp (progress) skalowania:
                     * 0 (środek) -> 1.0 (max wielkość)
                     * 1 (sąsiad) -> 0.65
                     * 2 (krawędź) -> 0.35
                     */
                    val progress = when (distance) {
                        0 -> 1.0f
                        1 -> 0.65f
                        2 -> 0.35f
                        else -> 0.0f
                    }

                    val fontSize = lerpSp(24.sp, 52.sp, progress)
                    val alpha = lerpFloat(0.15f, 1f, progress)
                    val isSelected = distance == 0

                    Box(
                        modifier = Modifier.width(itemWidthDp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = value.toString(),
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = fontSize,
                                color = (if (isSelected) Color(0xFFFF6D00) else Color.Black).copy(alpha = alpha)
                            )
                        )
                    }
                }
            }
        }

        // Strzałka pod 3. elementem (środkiem pickera o szerokości 5 elementów)
        Box(
            modifier = Modifier.width(totalPickerWidth),
            contentAlignment = Alignment.Center
        ) {
            TriangleIndicator(color = Color(0xFFFF6D00))
        }
    }
}

/**
 * Komponent rysujący pomarańczowy trójkąt wskazujący wybraną wartość.
 * @param color Kolor wypełnienia trójkąta.
 */
@Composable
fun TriangleIndicator(color: Color) {
    Canvas(modifier = Modifier.size(30.dp, 18.dp)) {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        drawPath(path = path, color = color)
    }
}

/**
 * Liniowa interpolacja dla jednostek TextUnit (rozmiar czcionki).
 */
private fun lerpSp(start: TextUnit, end: TextUnit, fraction: Float): TextUnit =
    (start.value + (end.value - start.value) * fraction).sp

/**
 * Liniowa interpolacja dla wartości Float (przezroczystość).
 */
private fun lerpFloat(start: Float, end: Float, fraction: Float): Float =
    start + (end - start) * fraction