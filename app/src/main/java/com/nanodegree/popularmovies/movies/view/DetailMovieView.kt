package com.nanodegree.popularmovies.movies.view

import com.nanodegree.popularmovies.dto.CastDTO
import com.nanodegree.popularmovies.dto.MovieDetailDTO

/**
 * Created by luan_ on 03/09/2017.
 */
interface DetailMovieView {
    fun showDetailMovie(movieDetail: MovieDetailDTO)
    fun showCast(cast: ArrayList<CastDTO>)
}