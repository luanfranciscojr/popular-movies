package com.nanodegree.popularmovies.movies.module

import com.nanodegree.popularmovies.common.CustomScope
import com.nanodegree.popularmovies.movies.view.DetailMovieView
import dagger.Module
import dagger.Provides

/**
 * Created by luan_ on 04/06/2017.
 */


@Module
class DetailMovieModule(private val mMovieView: DetailMovieView) {

    @Provides
    @CustomScope
    internal fun providerMovieDetailView(): DetailMovieView {
        return mMovieView
    }
}