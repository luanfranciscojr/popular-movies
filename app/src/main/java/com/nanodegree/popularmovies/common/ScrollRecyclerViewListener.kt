package com.nanodegree.popularmovies.common

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView



/**
 * Created by luan_ on 02/09/2017.
 */
open class ScrollRecyclerViewListener(private var layoutManager: GridLayoutManager, private var loadMoreItemsListener: LoadMoreItemsListener): RecyclerView.OnScrollListener() {

    var isLoading: Boolean = false
    var isLastPage: Boolean = false
    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) = super.onScrollStateChanged(recyclerView, newState)

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                loadMoreItemsListener.loadMoreItems()
            }
        }

    }


}