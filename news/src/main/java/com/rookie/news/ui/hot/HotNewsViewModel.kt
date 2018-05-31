package com.rookie.news.ui.hot

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.Transformations.switchMap
import com.rookie.news.base.BaseViewModel
import com.rookie.news.pojo.response.News
import com.rookie.news.repository.Listing
import com.rookie.news.repository.hot.HotNewsRepository

/**
 * Author: FK
 * Date：  2018/5/30.
 */
class HotNewsViewModel(val repo: HotNewsRepository) : BaseViewModel() {
    private val repoResult = MutableLiveData<Listing<News>>()
    val refreshState = switchMap(repoResult, { it.refreshState })
    val loadMoreState = Transformations.switchMap(repoResult, { it.loadMoreState })
    val news = Transformations.switchMap(repoResult, { it.pagedList })

    fun lazyLoadData(){
        repoResult.postValue(repo.getDatas(15))
    }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }
}