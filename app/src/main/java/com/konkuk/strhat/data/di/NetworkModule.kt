package com.konkuk.strhat.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.konkuk.strhat.BuildConfig
import com.konkuk.strhat.core.network.TokenAuthenticator
import com.konkuk.strhat.core.network.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun providesAuthorizationInterceptor(
        tokenManager: TokenManager
    ): okhttp3.Interceptor = okhttp3.Interceptor { chain ->
        val request = chain.request()
        val url = request.url.toString()

        if (!shouldAddAuthorization(url)) {
            return@Interceptor chain.proceed(request)
        }

        val token = tokenManager.getToken()

        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    fun providesTokenAuthenticator(
        tokenManager: TokenManager,
        retrofit: dagger.Lazy<Retrofit>
    ): TokenAuthenticator = TokenAuthenticator(tokenManager, retrofit)

    private fun shouldAddAuthorization(url: String): Boolean {
        return !url.contains("/api/v1/auth/kakao") &&
                !url.contains("/api/v1/users/sign-up")
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: okhttp3.Interceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authorizationInterceptor)
        .authenticator(tokenAuthenticator)
        .build()

    @Provides
    @Singleton
    fun providesConverterFactory(): Converter.Factory =
        Json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()
}