package com.rookie.news.repository.news

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.rookie.news.pojo.response.News
import com.rookie.news.pojo.response.NewsResponse
import com.rookie.news.pojo.response.Response

/**
 * Author: FK
 * Date：  2018/5/30.
 */
class NewsDataSourceFactory(val category: String) :
        DataSource.Factory<Response<NewsResponse>, News>() {
    val sourceLiveData = MutableLiveData<NewsPageKeyedDataSource>()

    override fun create(): DataSource<Response<NewsResponse>, News> {
        return NewsPageKeyedDataSource(category)
    }
}