package com.rookie.news.ui

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.MainThread
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rookie.news.base.BaseViewModel
import com.rookie.news.common.api.Api
import com.rookie.news.common.rxjava.NewsObserver
import com.rookie.news.common.rxjava.RxJavaPlugin
import com.rookie.news.common.util.SpUtil
import com.rookie.news.pojo.response.Category
import com.rookie.news.pojo.response.Data
import com.rookie.news.pojo.response.NewsResponse

/**
 * Author: FK
 * Date：  2018/5/25.
 */
class MainViewModel : BaseViewModel() {
    private val TAG = "MainViewModel"
    private val categories = MutableLiveData<List<Category>>()
    private val allNews = MutableLiveData<NewsResponse>()
    private val hotNews = MutableLiveData<NewsResponse>()
    private val selectionNews = MutableLiveData<NewsResponse>()

    @MainThread
    fun getCategories(): MutableLiveData<List<Category>> {
        val oldStr = SpUtil.getSp().getString("Categories", null)
        oldStr?.let {
            val list: List<Category> = Gson().fromJson(it, object : TypeToken<List<Category>>() {}.type)
            categories.postValue(list)
        }
        Api.getCategories()
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : NewsObserver<Data>(this) {
                    override fun onSuccess(t: Data) {
                        val str = Gson().toJson(t.categories)
                        if (!TextUtils.equals(str, oldStr)) {
                            categories.postValue(t.categories)
                            SpUtil.put { editor -> editor.putString("Categories", str) }
                        }
                    }
                })
        return categories
    }

    @MainThread
    fun getAllNews(): MutableLiveData<NewsResponse> {
        Api.getAllNews("", "")
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : NewsObserver<NewsResponse>(this) {
                    override fun onSuccess(t: NewsResponse) {
                        allNews.postValue(t)
                    }
                })
        return allNews
    }

    @MainThread
    fun getSelectionNews(): MutableLiveData<NewsResponse> {
        Api.getSelectionNews()
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : NewsObserver<NewsResponse>(this) {
                    override fun onSuccess(t: NewsResponse) {
                        selectionNews.postValue(t)
                    }
                })
        return selectionNews
    }

    @MainThread
    fun getHotNews(): MutableLiveData<NewsResponse> {
        Api.getHotNews("10")
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : NewsObserver<NewsResponse>(this) {
                    override fun onSuccess(t: NewsResponse) {
                        hotNews.postValue(t)
                    }
                })
        return hotNews
    }
}