package com.rookie.demo.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.rookie.demo.common.api.Api
import com.rookie.demo.pojo.bean.LastThemeStory
import com.rookie.demo.pojo.bean.NetWorkState
import com.rookie.demo.pojo.response.GetLastThemeResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Author: FK
 * Date：  2018/5/16.
 */
class HotNewsPageKeyedDataSource : PageKeyedDataSource<GetLastThemeResponse, LastThemeStory>() {

    val initializeState: MutableLiveData<NetWorkState> = MutableLiveData()
    val loadAfterState: MutableLiveData<NetWorkState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<GetLastThemeResponse>,
                             callback: LoadInitialCallback<GetLastThemeResponse, LastThemeStory>) {
        Api.getLastTheme()
                .subscribe(object : Observer<GetLastThemeResponse> {
                    override fun onComplete() {
                        initializeState.postValue(NetWorkState.SUCCESS)
                    }

                    override fun onSubscribe(d: Disposable) {
                        initializeState.postValue(NetWorkState.LOADING)
                    }

                    override fun onNext(t: GetLastThemeResponse) {
                        callback.onResult(t.stories!!, t, t)
                    }

                    override fun onError(e: Throwable) {
                        initializeState.postValue(NetWorkState.error(e.message))
                    }
                })
    }

    override fun loadAfter(params: LoadParams<GetLastThemeResponse>,
                           callback: LoadCallback<GetLastThemeResponse, LastThemeStory>) {
        Api.getLastTheme()
                .subscribe(object : Observer<GetLastThemeResponse> {
                    override fun onComplete() {
                        loadAfterState.postValue(NetWorkState.SUCCESS)
                    }

                    override fun onSubscribe(d: Disposable) {
                        loadAfterState.postValue(NetWorkState.LOADING)
                    }

                    override fun onNext(t: GetLastThemeResponse) {
                        callback.onResult(t.stories!!, t)
                    }

                    override fun onError(e: Throwable) {
                        loadAfterState.postValue(NetWorkState.error(e.message))
                    }
                })
    }

    override fun loadBefore(params: LoadParams<GetLastThemeResponse>,
                            callback: LoadCallback<GetLastThemeResponse, LastThemeStory>) {

    }
}