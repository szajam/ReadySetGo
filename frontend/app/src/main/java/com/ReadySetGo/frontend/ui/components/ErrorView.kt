package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ReadySetGo.frontend.R
import com.ReadySetGo.frontend.ui.theme.ErrorRed
import com.ReadySetGo.frontend.ui.theme.ErrorRedLight

/**
 * Komponent wyświetlający komunikat błędu.
 * Używany do pokazania błędów walidacji lub błędów API
 * np. niezgodności haseł lub nieudanego logowania.
 *
 * @param message Treść komunikatu błędu do wyświetlenia.
 */
@Composable
fun ErrorMessage(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ErrorRedLight)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = null,
            tint = ErrorRed,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = message,
            color = ErrorRed,
            fontSize = 13.sp
        )
    }
}