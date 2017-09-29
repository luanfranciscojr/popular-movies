package com.nanodegree.popularmovies.service.component

import com.nanodegree.popularmovies.service.module.ServiceModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Created by luanfernandes on 31/08/17.
 */
@Singleton
@Component(modules = arrayOf(ServiceModule::class))
interface ServiceComponent {
    fun retrofit(): Retrofit
}
