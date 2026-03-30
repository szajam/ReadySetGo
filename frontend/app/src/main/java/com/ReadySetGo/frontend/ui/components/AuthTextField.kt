package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ReadySetGo.frontend.R
import com.ReadySetGo.frontend.ui.theme.*


/**
 * Reużywalne pole tekstowe dla formularzy logowania i rejestracji.
 * Obsługuje pola email, hasła oraz potwierdzenia hasła.
 *
 * Zachowanie obramowania:
 * - Brak obramowania gdy pole jest puste
 * - Zielone obramowanie ([LimeGreen]) gdy pole ma wartość
 * - Czerwone obramowanie ([ErrorRed]) gdy [isError] jest true
 *
 * @param value             Aktualna wartość pola tekstowego.
 * @param onValueChange     Callback wywoływany przy każdej zmianie wartości.
 * @param label             Etykieta wyświetlana nad polem tekstowym.
 * @param leadingIconRes    Id zasobu ikony wyświetlanej po lewej stronie pola.
 * @param modifier          Opcjonalny modifier dla dostosowania wyglądu.
 * @param isPassword        Czy pole jest polem hasła — ukrywa tekst i pokazuje ikonę oka. Domyślnie false.
 * @param isError           Czy pole jest w stanie błędu — wyświetla czerwone obramowanie. Domyślnie false.
 * @param keyboardType      Typ klawiatury, domyślnie [KeyboardType.Text].
 */
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIconRes: Int,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val borderColor = when {
        isError -> ErrorRed
        value.isNotEmpty() -> LimeGreen
        else -> Color.Transparent
    }

    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkNavy,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(InputGray)
                .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = leadingIconRes),
                        contentDescription = null,
                        tint = TextGray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = if (isPassword) {
                    {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(
                                    id = if (passwordVisible)
                                        R.drawable.ic_eye_on
                                    else
                                        R.drawable.ic_eye_off
                                ),
                                contentDescription = if (passwordVisible) "Hide" else "Show",
                                tint = TextGray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                } else null,
                visualTransformation = if (isPassword && !passwordVisible)
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                singleLine = true,
                isError = isError
            )
        }
    }
}