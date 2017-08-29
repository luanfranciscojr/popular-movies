package com.nanodegree.popularmovies.service.component

import android.content.SharedPreferences

import com.nanodegree.popularmovies.AppModule
import com.nanodegree.popularmovies.service.module.ServiceModule

import javax.inject.Singleton

import dagger.Component
import retrofit2.Retrofit

/**
 * Created by luan_ on 31/05/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, ServiceModule::class))
interface ServiceComponent {
    fun retrofit(): Retrofit
    fun sharedPreferences(): SharedPreferences
}
