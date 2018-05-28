package com.rookie.demo.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.rookie.demo.pojo.bean.LastThemeStory

/**
 * Author: FK
 * Date：  2018/5/16.
 */
class HotNewsRepository {
    fun post(): LiveData<PagedList<LastThemeStory>> {
        val sourceFactory = HotNewsDataSourceFactory()
        val pagedList = PagedList.Config
                .Builder()
                .setPageSize(10)
                .setPrefetchDistance(5)
                .build()
        return LivePagedListBuilder(sourceFactory, pagedList).build()
    }
}