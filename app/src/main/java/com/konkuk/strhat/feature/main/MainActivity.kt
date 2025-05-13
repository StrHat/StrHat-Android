package com.konkuk.strhat.feature.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.konkuk.strhat.core.network.TokenManager
import com.konkuk.strhat.core.util.AppRestartUtil
import com.konkuk.strhat.ui.theme.StrHatTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()

        tokenManager.setLogoutCallback {
            tokenManager.clear()
            AppRestartUtil.restartApp(applicationContext)
        }

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