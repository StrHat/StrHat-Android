package com.konkuk.strhat.feature.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.konkuk.strhat.ui.theme.StrHatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            StrHatTheme {
                MainScreen(
                    navigator = navigator
                )
            }
        }
    }
}