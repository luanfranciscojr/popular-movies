package com.nanodegree.popularmovies.movies.view.adapter

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nanodegree.popularmovies.R
import com.nanodegree.popularmovies.common.ItemClickListener
import com.nanodegree.popularmovies.dto.MovieDTO
import com.nanodegree.popularmovies.service.module.ServiceModule
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val context: Context, private var itemClickListener: ItemClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val imageWidth = "w500/"
    internal var movieList = ArrayList<MovieDTO>()

    override fun getItemCount(): Int = movieList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: MovieDTO = movieList[position]
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClickListener(holder.adapterPosition)
        }
        Picasso.with(context).load(ServiceModule.BASE_IMAGE_URL + imageWidth + movie.posterPath)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.movieImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieImage: AppCompatImageView = itemView.imageMovie

    }
}