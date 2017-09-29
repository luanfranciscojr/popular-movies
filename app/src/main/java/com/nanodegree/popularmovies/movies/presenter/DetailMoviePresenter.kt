package com.nanodegree.popularmovies.movies.presenter

import com.nanodegree.popularmovies.dto.CreditsDTO
import com.nanodegree.popularmovies.dto.MovieDetailDTO
import com.nanodegree.popularmovies.movies.view.DetailMovieView
import com.nanodegree.popularmovies.service.request.PopularMoviesRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by luan_ on 03/09/2017.
 */
class DetailMoviePresenter @Inject constructor(private val retrofit: Retrofit, private val view: DetailMovieView) : Callback<MovieDetailDTO> {


    fun loadMovieDetail(movieId: Long) {

        val popularMoviesRequest: PopularMoviesRequest = retrofit.create(PopularMoviesRequest::class.java)
        val call: Call<MovieDetailDTO> = popularMoviesRequest.detailMovie(movieId)
        call.enqueue(this)
    }

    override fun onResponse(call: Call<MovieDetailDTO>?, response: Response<MovieDetailDTO>) {
        if(response.isSuccessful){
            view.showDetailMovie(response.body())
        }
    }

    override fun onFailure(call: Call<MovieDetailDTO>?, t: Throwable?) {
    }


    fun loadCast(movieId: Long) {
        val popularMoviesRequest: PopularMoviesRequest = retrofit.create(PopularMoviesRequest::class.java)
        val call: Call<CreditsDTO> = popularMoviesRequest.creditsMovie(movieId)
        call.enqueue(object :Callback<CreditsDTO>{
            override fun onResponse(call: Call<CreditsDTO>, response: Response<CreditsDTO>) {
                if(response.isSuccessful){
                    view.showCast(response.body().cast)
                }

            }

            override fun onFailure(call: Call<CreditsDTO>?, t: Throwable?) {
            }


        })
    }



}
