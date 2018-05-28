package com.rookie.news.common.rxjava

import com.rookie.news.base.BaseViewModel
import com.rookie.news.pojo.bean.NetWorkState
import com.rookie.news.pojo.response.Response
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Author: FK
 * Date：  2018/5/25.
 */
abstract class NewsObserver<T>(private val viewModel: BaseViewModel) : Observer<Response<T>> {
    override fun onComplete() {
        viewModel.netWorkState.value = NetWorkState.SUCCESS
    }

    override fun onSubscribe(d: Disposable) {
        viewModel.netWorkState.value = NetWorkState.LOADING
    }

    override fun onNext(t: Response<T>) {
        if (t.success) {
            onSuccess(t.data)
        } else {
            viewModel.netWorkState.value = NetWorkState.error(t.msg)
        }
    }

    override fun onError(e: Throwable) {
        viewModel.netWorkState.value = NetWorkState.error(e.message)
    }

    abstract fun onSuccess(t: T)
}