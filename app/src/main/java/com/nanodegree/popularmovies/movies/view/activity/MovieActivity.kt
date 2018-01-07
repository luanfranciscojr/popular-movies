package com.nanodegree.popularmovies.movies.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.nanodegree.popularmovies.PopularMoviesApplication
import com.nanodegree.popularmovies.R
import com.nanodegree.popularmovies.common.ItemClickListener
import com.nanodegree.popularmovies.common.ScrollRecyclerViewListener
import com.nanodegree.popularmovies.dto.MovieDTO
import com.nanodegree.popularmovies.movies.component.DaggerMovieComponent
import com.nanodegree.popularmovies.movies.module.MovieModule
import com.nanodegree.popularmovies.movies.presenter.MoviePresenter
import com.nanodegree.popularmovies.movies.view.MovieView
import com.nanodegree.popularmovies.movies.view.adapter.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


/**
 * Created by luanfernandes on 31/08/17.
 */
class MovieActivity : AppCompatActivity(), MovieView {


    @Inject
    lateinit var presenter: MoviePresenter
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var scrollRecyclerViewListener: ScrollRecyclerViewListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        loadMovies()
    }

    private fun initViews() {
        movieAdapter = MovieAdapter(this, object: ItemClickListener{
            override fun onItemClickListener(position: Int) {
                val movie = movieAdapter.movieList[position]
                val movieDetailIntent = Intent(this@MovieActivity, DetailMovieActivity::class.java)
                movieDetailIntent.putExtra(DetailMovieActivity.MOVIE_KEY, movie)
                startActivity(movieDetailIntent)
            }

        })

        val gridLayoutManager = GridLayoutManager(this, 2)
        movieRecyclerView.layoutManager = gridLayoutManager
        movieRecyclerView.adapter = movieAdapter

        scrollRecyclerViewListener = object : ScrollRecyclerViewListener(gridLayoutManager,
                nextPage = {
                    this@MovieActivity.presenter.loadNextPage();
                }
        ) {}


        movieRecyclerView.addOnScrollListener(scrollRecyclerViewListener)
    }

    private fun setup() =
            DaggerMovieComponent.builder().serviceComponent((applicationContext as PopularMoviesApplication)
                    .serviceComponent)
                    .movieModule(MovieModule(this)).build().inject(this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        resetRecyclerMovies()
        when (item.itemId) {
            R.id.popular -> presenter.loadPopularMovies()
            R.id.top_rated -> presenter.loadTopRatedMovies()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pop_movies, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun loadMovies() {
        resetRecyclerMovies()
        presenter.loadPopularMovies()
    }

    private fun resetRecyclerMovies() {
        movieAdapter.movieList.clear()
        presenter.currentPage = 1
    }


    override fun showResult(movies: List<MovieDTO>) {
        movieAdapter.movieList.addAll(movies)
        movieAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        movieRecyclerView.visibility = View.GONE

    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        movieRecyclerView.visibility = View.VISIBLE
    }


}
