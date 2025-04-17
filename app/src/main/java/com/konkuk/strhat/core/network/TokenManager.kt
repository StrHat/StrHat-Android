package com.konkuk.strhat.core.network

import android.content.SharedPreferences
import com.konkuk.strhat.BuildConfig.TOKEN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun getToken(): String {
        return sharedPreferences.getString(TOKEN, "").orEmpty()
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }
}