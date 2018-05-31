package com.rookie.news.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.Transformations.map
import android.arch.lifecycle.Transformations.switchMap
import android.util.Log
import com.rookie.news.base.BaseViewModel
import com.rookie.news.pojo.response.Category
import com.rookie.news.pojo.response.News
import com.rookie.news.repository.Listing
import com.rookie.news.repository.news.NewsRepository

/**
 * Author: FK
 * Date：  2018/5/28.
 */
class NewsViewModel(private val repo: NewsRepository) : BaseViewModel() {
    val category = MutableLiveData<Category>()
    private var repoResult: MutableLiveData<Listing<News>> = MutableLiveData()
    val refreshState = switchMap(repoResult, { it.refreshState })
    val loadMoreState = Transformations.switchMap(repoResult, { it.loadMoreState })
    val news = Transformations.switchMap(repoResult, { it.pagedList })

    fun lazyLoadData() {
        repoResult.value = repo.getDatas(category.value?.alias ?: "", 15)
    }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }
}