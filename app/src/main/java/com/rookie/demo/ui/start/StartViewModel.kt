package com.rookie.demo.ui.start

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.rookie.demo.base.BaseViewModel
import com.rookie.demo.common.api.Api
import com.rookie.demo.pojo.response.GetStartInfoResponse
import com.rookie.demo.common.rxjava.RxJavaPlugin
import com.rookie.demo.common.rxjava.SimpleObserver

/**
 * Author: FK
 * Date：  2018/4/24.
 */
class StartViewModel : BaseViewModel() {

    private val startInfo: MutableLiveData<GetStartInfoResponse> = MutableLiveData()

    fun getStartInfo(): LiveData<GetStartInfoResponse> {
        Api.getStartInfo(1080, 1776)
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : SimpleObserver<GetStartInfoResponse>(this) {
                    override fun onNext(response: GetStartInfoResponse) {
                        startInfo.value = response
                    }
                })
        return startInfo
    }
}