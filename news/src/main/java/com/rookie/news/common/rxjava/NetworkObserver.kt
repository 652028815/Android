package com.rookie.news.common.rxjava

import android.arch.lifecycle.MutableLiveData
import com.rookie.news.pojo.bean.NetworkState
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Author: FK
 * Date：  2018/5/30.
 */
abstract class NetworkObserver<T>(private val networkState: MutableLiveData<NetworkState>) : Observer<T> {
    override fun onSubscribe(d: Disposable) {
        networkState.postValue(NetworkState.LOADING)
    }

    override fun onComplete() {
        networkState.postValue(NetworkState.SUCCESS)
    }

    override fun onError(e: Throwable) {
        networkState.postValue(NetworkState.error(e.message))
    }
}