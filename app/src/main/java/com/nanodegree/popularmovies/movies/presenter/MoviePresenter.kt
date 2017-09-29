package com.nanodegree.popularmovies.movies.presenter

import com.nanodegree.popularmovies.dto.GenericDTO
import com.nanodegree.popularmovies.dto.MovieDTO
import com.nanodegree.popularmovies.movies.view.MovieView
import com.nanodegree.popularmovies.service.request.PopularMoviesRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by luanfernandes on 31/08/17.
 */
class MoviePresenter @Inject constructor(private val retrofit: Retrofit, private val view: MovieView) : Callback<GenericDTO<MovieDTO>> {

    var currentPage: Int = 1
    var isSearchRequest = false
    var isLastPage: Boolean = true
    var query: String = ""
    var movies: GenericDTO<MovieDTO>? = null

    fun search() {
        isSearchRequest = true
        if (isFirstPage()) {
            view.showProgress()
        }
        view.notifyIsLoading(true)
        val popularMoviesRequest: PopularMoviesRequest = retrofit.create(PopularMoviesRequest::class.java)
        val call: Call<GenericDTO<MovieDTO>> = popularMoviesRequest.searchMovieByName(query, currentPage)
        call.enqueue(this)
    }

    fun loadMovies() {
        isSearchRequest = false
        if (isFirstPage()) {
            view.showProgress()
        }
        view.notifyIsLoading(true)
        val popularMoviesRequest: PopularMoviesRequest = retrofit.create(PopularMoviesRequest::class.java)
        val call: Call<GenericDTO<MovieDTO>> = popularMoviesRequest.popularMovies(currentPage)
        call.enqueue(this)
    }


    override fun onFailure(call: Call<GenericDTO<MovieDTO>>?, t: Throwable?) {
        if (isFirstPage()) view.hideProgress()
        view.notifyIsLoading(false)
        currentPage = movies?.page?:1
    }

    override fun onResponse(call: Call<GenericDTO<MovieDTO>>, response: Response<GenericDTO<MovieDTO>>) {
        if (response.isSuccessful) {
            movies = response.body()
            view.showResult(response.body().results)
            isLastPage = movies?.totalPages == currentPage
        } else {
            currentPage = movies?.page?:1
        }
        view.notifyIsLoading(false)
        if (isFirstPage()) view.hideProgress()

    }

    fun nextPage() {
        currentPage++
    }

    private fun isFirstPage() = currentPage == 1

}
