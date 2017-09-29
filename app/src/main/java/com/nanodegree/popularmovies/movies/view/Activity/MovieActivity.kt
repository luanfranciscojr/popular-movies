package com.nanodegree.popularmovies.movies.view.Activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.nanodegree.popularmovies.PopularMoviesApplication
import com.nanodegree.popularmovies.R
import com.nanodegree.popularmovies.common.ItemClickListener
import com.nanodegree.popularmovies.common.LoadMoreItemsListener
import com.nanodegree.popularmovies.common.ScrollRecyclerView
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
class MovieActivity : AppCompatActivity(), MovieView, LoadMoreItemsListener, ItemClickListener {


    @Inject
    lateinit var presenter: MoviePresenter;
    lateinit var movieAdapter: MovieAdapter;
    lateinit var scrollRecyclerView: ScrollRecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        loadMovies()
    }

    private fun initViews(){
        val gridLayoutManager = GridLayoutManager(this,2)
        movieRecyclerView.layoutManager = gridLayoutManager
        val itemClickListener = this
        movieAdapter =  MovieAdapter(this,itemClickListener)
        movieRecyclerView.adapter = movieAdapter;
        scrollRecyclerView = ScrollRecyclerView(gridLayoutManager,this)
        movieRecyclerView.addOnScrollListener(scrollRecyclerView)
    }
    private fun setup() {
        DaggerMovieComponent.builder().serviceComponent((applicationContext as PopularMoviesApplication)
                .serviceComponent)
                .movieModule(MovieModule(this)).build().inject(this);

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_pop_movies, menu)
        var searchItem = menu.findItem(R.id.searchView)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager;
        val searchView: SearchView = searchItem.getActionView() as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean = true

            override fun onQueryTextSubmit(query: String): Boolean {
                resetRecyclerMovies()
                presenter.query = query
                presenter.search()
                return true
            }
        }
        searchView.setOnCloseListener (object:SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                loadMovies()
                searchView.onActionViewCollapsed()
                return  true
            }

        })
        searchView.setOnQueryTextListener(queryTextListener)
        return true
    }

    private fun loadMovies() {
        resetRecyclerMovies()
        presenter.loadMovies()
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

    override fun onItemClick(position: Int) {
        val movie = movieAdapter.movieList.get(position)
        val movieDetailIntent = Intent(this, DetailMovieActivity::class.java)
        movieDetailIntent.putExtra(DetailMovieActivity.MOVIE_KEY,movie)
        startActivity(movieDetailIntent)
    }


    override fun notifyIsLoading(isLoading: Boolean) {
        scrollRecyclerView.isLoading = isLoading
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        movieRecyclerView.visibility = View.VISIBLE
    }

    override fun loadMoreItens() {
        presenter.nextPage()
        if(presenter.isSearchRequest){
            presenter.search()

        }else{
            presenter.loadMovies()
        }
        scrollRecyclerView.isLastPage = presenter.isLastPage
    }
}
