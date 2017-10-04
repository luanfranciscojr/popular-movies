package com.nanodegree.popularmovies.movies.module

import com.nanodegree.popularmovies.common.CustomScope
import com.nanodegree.popularmovies.movies.view.MovieView
import dagger.Module
import dagger.Provides

/**
 * Created by luan_ on 04/06/2017.
 */


@Module
class MovieModule(private val mView: MovieView) {

    @Provides
    @CustomScope
    internal fun providerMovieView(): MovieView = mView
}
