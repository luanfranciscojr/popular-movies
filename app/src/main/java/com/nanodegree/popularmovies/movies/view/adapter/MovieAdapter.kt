package com.nanodegree.popularmovies.movies.view.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nanodegree.popularmovies.R
import com.nanodegree.popularmovies.common.ItemClickListener
import com.nanodegree.popularmovies.dto.MovieDTO
import com.nanodegree.popularmovies.service.module.ServiceModule
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter(private val context: Context, var itemClickListener: ItemClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val imageWidth = "w500/"
    internal var movieList = ArrayList<MovieDTO>();
    var calendar: Calendar = Calendar.getInstance()

    override fun getItemCount(): Int {
        return movieList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: MovieDTO = movieList.get(position)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
        Picasso.with(context).load(ServiceModule.BASE_IMAGE_URL + imageWidth + movie.posterPath)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.movieImage);
        holder.name.text = movie.title
        if (movie.releaseDate != null) {
            calendar.time = movie.releaseDate;
            var year = calendar.get(Calendar.YEAR)
            holder.year.text = year.toString()
            if (year == Calendar.getInstance().get(Calendar.YEAR)) {
                holder.name.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
                holder.year.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
            } else {
                holder.name.setTextColor(ContextCompat.getColor(context, android.R.color.black))
                holder.year.setTextColor(ContextCompat.getColor(context, android.R.color.black))

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieImage: AppCompatImageView = itemView.imageMovie
        val name: TextView = itemView.name
        val year: TextView = itemView.year
    }
}