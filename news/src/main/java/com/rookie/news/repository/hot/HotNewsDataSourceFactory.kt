package com.rookie.news.repository.hot

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.rookie.news.base.BaseViewModel
import com.rookie.news.pojo.response.News
import com.rookie.news.pojo.response.NewsResponse
import com.rookie.news.pojo.response.Response

/**
 * Author: FK
 * Date：  2018/5/30.
 */
class HotNewsDataSourceFactory :
        DataSource.Factory<Response<NewsResponse>, News>() {
    val sourceLiveData = MutableLiveData<HotNewsPageKeyedDataSource>()

    override fun create(): DataSource<Response<NewsResponse>, News> {
        val dataSource = HotNewsPageKeyedDataSource()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }
}