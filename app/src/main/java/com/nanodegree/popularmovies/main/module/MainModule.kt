package com.nanodegree.popularmovies.main.module

import com.nanodegree.popularmovies.common.CustomScope
import com.nanodegree.popularmovies.main.view.MainView
import com.nanodegree.popularmovies.model.dto.MovieDTO

import dagger.Module
import dagger.Provides

/**
 * Created by luan_ on 04/06/2017.
 */

@Module
class MainModule(private val mView: MainView) {

    @Provides
    @CustomScope
    internal fun providerMainView(): MainView {
        return mView
    }
}
