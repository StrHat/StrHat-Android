package com.konkuk.strhat.core.util

import android.content.Context
import android.content.Intent
import com.konkuk.strhat.feature.main.MainActivity

object AppRestartUtil {
    fun restartApp(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        context.startActivity(intent)
    }
}
