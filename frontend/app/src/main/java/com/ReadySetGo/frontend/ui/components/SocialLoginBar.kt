package com.ReadySetGo.frontend.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ReadySetGo.frontend.R

/**
 * Komponent wyświetlający rząd ikon logowania przez SM.
 * Zgodny z designem: białe tło, szare obramowanie, czarne ikony.
 */
@Composable
fun SocialLoginBar(
    onInstagramClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onLinkedinClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Instagram
        SocialIconButton(
            iconResId = R.drawable.ic_instagram,
            contentDescription = "Log in with Instagram",
            onClick = onInstagramClick
        )

        Spacer(modifier = Modifier.width(20.dp))

        //Fb
        SocialIconButton(
            iconResId = R.drawable.ic_facebook,
            contentDescription = "Log in with Facebook",
            onClick = onFacebookClick
        )

        Spacer(modifier = Modifier.width(20.dp))

        //LinkedIn
        SocialIconButton(
            iconResId = R.drawable.ic_linkedin,
            contentDescription = "Log in with LinkedIn",
            onClick = onLinkedinClick
        )
    }
}

/**
 * @param Wewnętrzny, reużywalny komponent dla pojedynczej ikony w kółku.
 */
@Composable
private fun SocialIconButton(
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {

    val customShape = RoundedCornerShape(16.dp)

    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp)
            .background(Color.White, customShape)
            .border(1.dp, Color(0xFFE0E0E0), customShape)
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SocialLoginBarPreview() {

    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(Color.White)
    ) {
        SocialLoginBar(
            onInstagramClick = {},
            onFacebookClick = {},
            onLinkedinClick = {}
        )
    }
}
