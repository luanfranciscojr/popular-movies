package com.nanodegree.popularmovies.movies.component

import com.nanodegree.popularmovies.common.CustomScope
import com.nanodegree.popularmovies.movies.module.MovieModule
import com.nanodegree.popularmovies.movies.view.activity.MovieActivity
import com.nanodegree.popularmovies.service.component.ServiceComponent
import dagger.Component

/**
 * Created by luanfernandes on 31/08/17.
 */
@CustomScope
@Component(dependencies = arrayOf(ServiceComponent::class), modules = arrayOf(MovieModule::class))
interface MovieComponent {
    fun inject(mainActivity: MovieActivity)
}
