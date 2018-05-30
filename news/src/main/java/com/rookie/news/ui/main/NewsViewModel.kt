package com.rookie.news.ui.main

import android.arch.lifecycle.MutableLiveData
import com.rookie.news.base.BaseViewModel
import com.rookie.news.common.api.Api
import com.rookie.news.common.rxjava.NewsObserver
import com.rookie.news.common.rxjava.RxJavaPlugin
import com.rookie.news.pojo.response.Category
import com.rookie.news.pojo.response.NewsResponse

/**
 * Author: FK
 * Date：  2018/5/28.
 */
class NewsViewModel : BaseViewModel() {
    val allNews = MutableLiveData<NewsResponse>()
    var category: Category? = null

    fun getNews(): MutableLiveData<NewsResponse> {
        Api.getAllNews(category?.alias ?: "", "15")
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : NewsObserver<NewsResponse>(this) {
                    override fun onSuccess(t: NewsResponse) {
                        allNews.postValue(t)
                    }
                })
        return allNews
    }
}