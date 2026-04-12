package com.ReadySetGo.frontend.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ReadySetGo.frontend.R
import com.ReadySetGo.frontend.ui.theme.*


/**
 * Widok ekranu On Board'ingu — czysty UI bez logiki biznesowej.
 * Wyświetla zdjęcie w tle, kartę z tekstem, wskaźnik postępu oraz przycisk "Get Started".
 *
 * @param onGetStarted Callback wywoływany po kliknięciu przycisku "Get Started".
 */
@Composable
fun OnboardingView(onGetStarted: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {

        // ── Background photo placeholder ─────────────────────────
        Image(
            painter = painterResource(id = R.drawable.ic_onboarding_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // ── Bottom card ──────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(White)
                .padding(horizontal = 28.dp, vertical = 36.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // Title
                Text(
                    text = buildAnnotatedString {
                        append("Wherever You Are\n")
                        withStyle(SpanStyle(color = LimeGreen)) {
                            append("Health")
                        }
                        append(" Is Number One")
                    },
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )

                // Subtitle
                Text(
                    text = "There is no instant way to a healthy life",
                    fontSize = 14.sp,
                    color = TextGray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Progress dots
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(6.dp)
                            .clip(CircleShape)
                            .background(LimeGreen)
                    )
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDDDDDD))
                    )
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDDDDDD))
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Button
                Button(
                    onClick = onGetStarted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkNavy
                    )
                ) {
                    Text(
                        text = "Get Started →",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White
                    )
                }
            }
        }
    }
}

/**
 * Podgląd ekranu onboardingu w Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun OnboardingViewPreview() {
    ReadySetGoTheme {
        OnboardingView(onGetStarted = {})
    }
}