package com.ReadySetGo.frontend.ui.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ReadySetGo.frontend.R
import com.ReadySetGo.frontend.ui.components.AuthTextField
import com.ReadySetGo.frontend.ui.components.ErrorMessage
import com.ReadySetGo.frontend.ui.components.PrimaryButton
import com.ReadySetGo.frontend.ui.theme.*


/**
 * Widok ekranu Rejestracji — czysty UI bez logiki biznesowej.
 * Wyświetla formularz rejestracji z polami email, hasła, potwierdzenia hasła
 * oraz walidacją zgodności haseł.
 *
 * @param email                     Aktualna wartość pola email.
 * @param onEmailChange             Callback wywoływany przy zmianie wartości pola email.
 * @param password                  Aktualna wartość pola hasła.
 * @param onPasswordChange          Callback wywoływany przy zmianie wartości pola hasła.
 * @param confirmPassword           Aktualna wartość pola potwierdzenia hasła.
 * @param onConfirmPasswordChange   Callback wywoływany przy zmianie wartości pola potwierdzenia hasła.
 * @param isLoading                 Czy trwa proces rejestracji — wyświetla wskaźnik ładowania na przycisku.
 * @param error                     Komunikat błędu do wyświetlenia, lub null jeśli brak błędu.
 * @param passwordMismatch          Czy hasła nie są zgodne — wyświetla komunikat błędu.
 * @param onSignUpClick             Callback wywoływany po kliknięciu przycisku "Sign Up".
 * @param onNavigateToSignIn        Callback przekierowujący z powrotem do ekranu logowania.
 */
@Composable
fun SignUpView(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    isLoading: Boolean,
    error: String?,
    passwordMismatch: Boolean,
    onSignUpClick: () -> Unit,
    onNavigateToSignIn: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(LimeGreen, Color.White),
                    startY = 0f,
                    endY = 600f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "ReadySetGo Logo",
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "Sign Up For Free",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Quickly make your account in 1 minute",
                fontSize = 14.sp,
                color = DarkNavy.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Form card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AuthTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = "Email Address",
                    leadingIconRes = R.drawable.ic_email,
                    keyboardType = KeyboardType.Email
                )

                AuthTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = "Password",
                    leadingIconRes = R.drawable.ic_lock,
                    isPassword = true
                )

                AuthTextField(
                    value = confirmPassword,
                    onValueChange = onConfirmPasswordChange,
                    label = "Confirm Password",
                    leadingIconRes = R.drawable.ic_lock,
                    isPassword = true,
                    isError = passwordMismatch
                )

                if (passwordMismatch) {
                    ErrorMessage(message = "ERROR: Password Don't Match!")
                }

                if (error != null) {
                    ErrorMessage(message = error)
                }

                Spacer(modifier = Modifier.height(4.dp))

                PrimaryButton(
                    text = "Sign Up",
                    onClick = onSignUpClick,
                    isLoading = isLoading
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = onNavigateToSignIn) {
                        Text(
                            text = buildAnnotatedString {
                                append("Already have an account? ")
                                withStyle(SpanStyle(color = Orange, fontWeight = FontWeight.SemiBold)) {
                                    append("Sign In.")
                                }
                            },
                            fontSize = 14.sp,
                            color = DarkNavy
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

/**
 * Podgląd ekranu rejestracji w Android Studio z przykładowym błędem niezgodności haseł.
 */
@Preview(showBackground = true)
@Composable
fun SignUpViewPreview() {
    ReadySetGoTheme {
        SignUpView(
            email = "test@test.com",
            onEmailChange = {},
            password = "password123",
            onPasswordChange = {},
            confirmPassword = "wrong",
            onConfirmPasswordChange = {},
            isLoading = false,
            error = null,
            passwordMismatch = true,
            onSignUpClick = {},
            onNavigateToSignIn = {}
        )
    }
}