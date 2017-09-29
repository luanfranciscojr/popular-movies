package com.nanodegree.popularmovies.movies.component

import com.nanodegree.popularmovies.common.CustomScope
import com.nanodegree.popularmovies.movies.module.DetailMovieModule
import com.nanodegree.popularmovies.movies.view.Activity.DetailMovieActivity
import com.nanodegree.popularmovies.service.component.ServiceComponent
import dagger.Component

/**
 * Created by luanfernandes on 31/08/17.
 */
@CustomScope
@Component(dependencies = arrayOf(ServiceComponent::class), modules = arrayOf(DetailMovieModule::class))
interface DetailMovieComponent {
    fun inject(mainActivity: DetailMovieActivity)
}