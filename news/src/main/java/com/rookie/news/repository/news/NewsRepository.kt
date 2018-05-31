package com.rookie.news.repository.news

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.rookie.news.pojo.response.News
import com.rookie.news.repository.Listing

/**
 * Author: FK
 * Date：  2018/5/30.
 */
class NewsRepository {

    fun getDatas(category: String, size: Int): Listing<News> {
        val sourceFactory = NewsDataSourceFactory(category)
        val pagedList = PagedList.Config
                .Builder()
                .setPageSize(size)
                .setPrefetchDistance(5)
                .build()
        val livePagedList = LivePagedListBuilder(sourceFactory, pagedList).build()
        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.refreshState
        }
        val loadMoreState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.loadMoreState
        }

        return Listing(livePagedList,
                refreshState,
                loadMoreState,
                {
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                { sourceFactory.sourceLiveData.value?.retry() })
    }
}