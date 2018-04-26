package com.rookie.demo.ui.main

import android.arch.lifecycle.MutableLiveData
import com.rookie.demo.base.BaseViewModel
import com.rookie.demo.common.api.Api
import com.rookie.demo.pojo.response.GetAllThemesResponse
import com.rookie.demo.common.rxjava.RxJavaPlugin
import com.rookie.demo.common.rxjava.SimpleObserver

/**
 * Author: FK
 * Date：  2018/4/24.
 */
class MainViewModel : BaseViewModel() {
    private val allThemes: MutableLiveData<GetAllThemesResponse> = MutableLiveData()

    fun getAllThemes():MutableLiveData<GetAllThemesResponse>{
        Api.getAllThemes()
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : SimpleObserver<GetAllThemesResponse>(this){
                    override fun onNext(t: GetAllThemesResponse) {
                        allThemes.value = t
                    }
                })
        return allThemes
    }
}