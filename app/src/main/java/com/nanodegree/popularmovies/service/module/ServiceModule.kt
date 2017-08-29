package com.nanodegree.popularmovies.service.module

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.io.IOException

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by luanfernandes on 18/04/17.
 */

@Module
class ServiceModule {

    private val baseUrl = " http://image.tmdb.org/t/p/"
    private val apiKey = ""

    @Provides
    @Singleton
    internal fun providesSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter("api_key", apiKey).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        return client.build()
    }

    @Provides
    @Singleton
    internal fun retrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
                .baseUrl(baseUrl)
                .build()
    }
}
