package com.example.base2023.di

import com.example.base2023.data.network.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUniInternalApi(
        okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory
    ): ApiService {

        return Retrofit.Builder()
            .baseUrl("https://mp3ringtonesdownload.net/wp-content/uploads/2023/06/")
            .addConverterFactory(moshiConverterFactory).client(okHttpClient).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(
//        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
//        builder.interceptors().add(interceptor)
        builder.interceptors().add(httpLoggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

//    @Provides
//    @Singleton
//    fun provideHeaderInterceptor(): Interceptor = Interceptor { chain ->
//        val builder = chain.request().newBuilder()
//        builder.header("language", AppData.language)
//        AppData.accessToken?.let {
//            builder.header("Authorization", "Bearer $it")
//        }
//        chain.proceed(builder.build())
//    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

}