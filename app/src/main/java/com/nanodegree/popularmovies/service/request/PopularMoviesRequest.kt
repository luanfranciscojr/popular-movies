package com.nanodegree.popularmovies.service.request

import com.nanodegree.popularmovies.dto.CreditsDTO
import com.nanodegree.popularmovies.dto.GenericDTO
import com.nanodegree.popularmovies.dto.MovieDTO
import com.nanodegree.popularmovies.dto.MovieDetailDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by luanfernandes on 31/08/17.
 */
interface PopularMoviesRequest{

    @GET("search/movie")
    fun searchMovieByName(@Query("query") movieName:String,@Query("page") page:Int): Call<GenericDTO<MovieDTO>>


    @GET("movie/popular")
    fun popularMovies(@Query("page") page:Int): Call<GenericDTO<MovieDTO>>


    @GET("movie/{movie_id}")
    fun detailMovie(@Path("movie_id") movieId: Long): Call<MovieDetailDTO>

    @GET("movie/{movie_id}/credits")
    fun creditsMovie(@Path("movie_id") movieId: Long): Call<CreditsDTO>
}
