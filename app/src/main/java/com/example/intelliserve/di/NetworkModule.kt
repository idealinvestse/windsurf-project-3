package com.example.intelliserve.di

import com.example.intelliserve.data.api.GroqApiService
import com.example.intelliserve.data.api.XAIApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class XAIRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GroqRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val XAI_BASE_URL = "https://api.x.ai/"
    private const val GROQ_BASE_URL = "https://api.groq.com/"
    
    // TODO: Replace with your actual API keys
    private const val XAI_API_KEY = "YOUR_XAI_API_KEY"
    private const val GROQ_API_KEY = "YOUR_GROQ_API_KEY"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideXAIAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $XAI_API_KEY")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideGroqAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $GROQ_API_KEY")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    @XAIRetrofit
    fun provideXAIOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        xaiAuthInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(xaiAuthInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @GroqRetrofit
    fun provideGroqOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        groqAuthInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(groqAuthInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @XAIRetrofit
    fun provideXAIRetrofit(@XAIRetrofit okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(XAI_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @GroqRetrofit
    fun provideGroqRetrofit(@GroqRetrofit okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GROQ_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideXAIApiService(@XAIRetrofit retrofit: Retrofit): XAIApiService {
        return retrofit.create(XAIApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGroqApiService(@GroqRetrofit retrofit: Retrofit): GroqApiService {
        return retrofit.create(GroqApiService::class.java)
    }
}
