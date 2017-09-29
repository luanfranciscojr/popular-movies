package com.nanodegree.popularmovies.movies.view.Activity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.nanodegree.popularmovies.PopularMoviesApplication
import com.nanodegree.popularmovies.R
import com.nanodegree.popularmovies.dto.CastDTO
import com.nanodegree.popularmovies.dto.MovieDTO
import com.nanodegree.popularmovies.dto.MovieDetailDTO
import com.nanodegree.popularmovies.movies.component.DaggerDetailMovieComponent
import com.nanodegree.popularmovies.movies.module.DetailMovieModule
import com.nanodegree.popularmovies.movies.presenter.DetailMoviePresenter
import com.nanodegree.popularmovies.movies.view.DetailMovieView
import com.nanodegree.popularmovies.movies.view.adapter.CastAdapter
import com.nanodegree.popularmovies.service.module.ServiceModule
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*
import java.util.*
import javax.inject.Inject


open class DetailMovieActivity : AppCompatActivity(), DetailMovieView {


    @Inject
    lateinit var presenter: DetailMoviePresenter;
    lateinit var itemsAdapter: CastAdapter
    private val imageWidth = "w500/"

    companion object {
        const val MOVIE_KEY = "MOVIE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        setContentView(R.layout.activity_detail_movie)
        initViews()
        val movie = intent.getParcelableExtra<MovieDTO>(MOVIE_KEY)
        presenter.loadMovieDetail(movie.id)
        presenter.loadCast(movie.id)
        showInformations(movie)
    }
    private fun initViews(){
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        creditsRecycler.layoutManager = linearLayoutManager
        itemsAdapter  = CastAdapter(this)
        creditsRecycler.adapter = itemsAdapter
        creditsRecycler.isNestedScrollingEnabled = false

    }
    private fun showInformations(movie: MovieDTO) {
        collapsingToolbar.title = movie.originalTitle
        Picasso.with(this).load(ServiceModule.BASE_IMAGE_URL + "w342/" + movie.posterPath)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageMovie);

    }

    private fun setup() {
        DaggerDetailMovieComponent.builder().serviceComponent((applicationContext as PopularMoviesApplication)
                .serviceComponent).detailMovieModule(DetailMovieModule(this)).build().inject(this)

    }


    override fun showDetailMovie(movieDetail: MovieDetailDTO) {
        Picasso.with(this)
                .load(ServiceModule.BASE_IMAGE_URL + imageWidth + movieDetail.backdropPath)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                    }

                    override fun onBitmapFailed(errorDrawable: Drawable?) {
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        val bitmapDrawable = BitmapDrawable(resources, bitmap)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            collapsingToolbar.setBackground(bitmapDrawable)
                        } else {
                            collapsingToolbar.setBackgroundDrawable(bitmapDrawable)
                        }
                    }

                })
        overview.text = movieDetail.overview ?: ""
        val calendar = Calendar.getInstance()
        calendar.time = movieDetail.releaseDate
        year.text = calendar.get(Calendar.YEAR).toString()
        runtime.text = resources.getString(R.string.detail_runtime, movieDetail.runtime ?: "")
        voteAverage.text = resources.getString(R.string.detail_vote_avarage,movieDetail.voteAverage.toString())
    }

    override fun showCast(cast: ArrayList<CastDTO>) {
        itemsAdapter.castList = cast
        itemsAdapter.notifyDataSetChanged()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
