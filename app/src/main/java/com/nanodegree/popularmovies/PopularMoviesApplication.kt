package com.nanodegree.popularmovies

import android.app.Application
import com.nanodegree.popularmovies.service.component.DaggerServiceComponent
import com.nanodegree.popularmovies.service.component.ServiceComponent
import com.nanodegree.popularmovies.service.module.ServiceModule


/**
 * Created by luan_ on 31/05/2017.
 */

class PopularMoviesApplication : Application() {

    lateinit var serviceComponent: ServiceComponent
        private set


    override fun onCreate() {
        super.onCreate()
        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(ServiceModule())
                .build()
    }
}
