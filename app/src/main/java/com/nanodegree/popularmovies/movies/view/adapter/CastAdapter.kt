package com.nanodegree.popularmovies.movies.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nanodegree.popularmovies.R
import com.nanodegree.popularmovies.dto.CastDTO
import com.nanodegree.popularmovies.service.module.ServiceModule
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.credit_item.view.*
import java.util.*

class CastAdapter(private val context: Context) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private var castList = ArrayList<CastDTO>()

    override fun getItemCount(): Int = castList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cast: CastDTO = castList[position]
        holder.name.text = cast.name
        Picasso.with(context).load(ServiceModule.BASE_IMAGE_URL + "w185/" + cast.profilePath)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.characterImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.credit_item, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterImage = itemView.castImage
        val name: TextView = itemView.castName
    }
}