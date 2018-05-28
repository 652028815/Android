package com.rookie.news.common.rxjava

import com.rookie.news.base.BaseViewModel
import com.rookie.news.pojo.bean.NetWorkState
import com.rookie.news.pojo.bean.NetWorkState.Companion.LOADING
import com.rookie.news.pojo.bean.NetWorkState.Companion.SUCCESS
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Author: FK
 * Date：  2018/4/24.
 */
abstract class SimpleObserver<T>(private val viewModel: BaseViewModel) : Observer<T> {

    override fun onSubscribe(d: Disposable) {
        viewModel.netWorkState.value = LOADING
    }

    override fun onComplete() {
        viewModel.netWorkState.value = SUCCESS
    }

    override fun onError(e: Throwable) {
        viewModel.netWorkState.value = NetWorkState.error(e.message)
    }
}