package com.nanodegree.popularmovies

import android.app.Application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by luan_ on 31/05/2017.
 */

@Module
class AppModule(internal var mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplicatmion(): Application {
        return mApplication
    }
}
