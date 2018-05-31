package com.rookie.news.common.rxjava

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.rookie.news.base.BaseViewModel
import com.rookie.news.pojo.bean.NetworkState
import com.rookie.news.pojo.response.Response
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Author: FK
 * Date：  2018/5/25.
 */
abstract class NewsObserver<T>(private val networkState: MutableLiveData<NetworkState>) : Observer<Response<T>> {
    override fun onComplete() {
        networkState.value = NetworkState.SUCCESS
    }

    override fun onSubscribe(d: Disposable) {
        networkState.value = NetworkState.LOADING
    }

    override fun onNext(t: Response<T>) {
        if (t.success) {
            onSuccess(t.data)
        } else {
            networkState.value = NetworkState.error(t.msg)
        }
    }

    override fun onError(e: Throwable) {
        networkState.value = NetworkState.error(e.message)
    }

    abstract fun onSuccess(t: T)
}