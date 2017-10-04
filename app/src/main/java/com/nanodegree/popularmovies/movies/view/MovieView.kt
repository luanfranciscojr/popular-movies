package com.nanodegree.popularmovies.movies.view

import com.nanodegree.popularmovies.dto.MovieDTO

/**
 * Created by luan_ on 21/08/2017.
 */

interface MovieView {
    fun showResult(movies: List<MovieDTO>)
    fun showProgress()
    fun hideProgress()
}
