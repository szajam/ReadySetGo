package com.ReadySetGo.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ReadySetGo.frontend.ui.navigation.AppNavigation
import com.ReadySetGo.frontend.ui.theme.ReadySetGoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReadySetGoTheme {
                AppNavigation()
            }
        }
    }
}