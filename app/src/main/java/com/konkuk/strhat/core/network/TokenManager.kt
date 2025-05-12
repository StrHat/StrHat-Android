package com.konkuk.strhat.core.network

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val KEY_REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val KEY_KAKAO_ID = "KAKAO_ID"
    }

    fun getToken(): String {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "").orEmpty()
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, token).apply()
    }

    fun getRefreshToken(): String {
        return sharedPreferences.getString(KEY_REFRESH_TOKEN, "").orEmpty()
    }

    fun saveRefreshToken(token: String) {
        sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, token).apply()
    }

    fun getKakaoId(): Long {
        return sharedPreferences.getLong(KEY_KAKAO_ID, -1L)
    }

    fun saveKakaoId(kakaoId: Long) {
        sharedPreferences.edit().putLong(KEY_KAKAO_ID, kakaoId).apply()
    }
}