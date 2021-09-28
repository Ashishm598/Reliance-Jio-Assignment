package com.android.jio.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class OnScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val loadMoreItems: LoadMoreItems
) :
    RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            loadMoreItems.onLoadMore()
            loading = true
        }
    }

    /**
     * To re-enable pagination on reaching the visibleThreshold after Pull-To-Refresh
     */
    fun resetLoadStatus() {
        previousTotal = 0
    }

    interface LoadMoreItems {
        fun onLoadMore()
    }
}