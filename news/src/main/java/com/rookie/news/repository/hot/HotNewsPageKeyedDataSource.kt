package com.rookie.news.repository.hot

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.rookie.news.App
import com.rookie.news.api.Api
import com.rookie.news.common.rxjava.NetworkObserver
import com.rookie.news.pojo.bean.NetworkState
import com.rookie.news.pojo.response.News
import com.rookie.news.pojo.response.NewsResponse
import com.rookie.news.pojo.response.Response

/**
 * Author: FK
 * Date：  2018/5/30.
 */
class HotNewsPageKeyedDataSource :
        PageKeyedDataSource<Response<NewsResponse>, News>() {
    val refreshState = MutableLiveData<NetworkState>()
    val loadMoreState = MutableLiveData<NetworkState>()
    private var retry: (() -> Any)? = null

    override fun loadInitial(params: LoadInitialParams<Response<NewsResponse>>, callback: LoadInitialCallback<Response<NewsResponse>, News>) {
        Api.getHotNews("15", null)
                .subscribe(object : NetworkObserver<Response<NewsResponse>>(refreshState) {
                    override fun onNext(t: Response<NewsResponse>) {
                        callback.onResult(t.data.news, t, t)
                    }
                })
        retry = { loadInitial(params, callback) }
    }

    override fun loadAfter(params: LoadParams<Response<NewsResponse>>, callback: LoadCallback<Response<NewsResponse>, News>) {
        Api.getHotNews("15", params.key.data.last_id)
                .subscribe(object : NetworkObserver<Response<NewsResponse>>(loadMoreState) {
                    override fun onNext(t: Response<NewsResponse>) {
                        callback.onResult(t.data.news, t)
                    }
                })
        retry = { loadAfter(params, callback) }
    }

    override fun loadBefore(params: LoadParams<Response<NewsResponse>>, callback: LoadCallback<Response<NewsResponse>, News>) {

    }

    fun retry() {
        App.networkThread.submit({
            retry?.invoke()
        })
    }
}