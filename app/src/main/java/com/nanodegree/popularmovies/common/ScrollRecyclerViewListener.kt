package com.nanodegree.popularmovies.common

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView


/**
 * Created by luan_ on 02/09/2017.
 */
open class ScrollRecyclerViewListener(private var layoutManager: GridLayoutManager, var nextPage: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0 && this.layoutManager.findLastVisibleItemPosition() == this.layoutManager.getItemCount() - 1) {
            nextPage()
        }

    }



}