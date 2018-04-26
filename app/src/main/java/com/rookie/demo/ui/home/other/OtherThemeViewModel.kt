package com.rookie.demo.ui.home.other

import android.arch.lifecycle.MutableLiveData
import com.rookie.demo.base.BaseViewModel
import com.rookie.demo.common.api.Api
import com.rookie.demo.pojo.response.GetThemeResponse
import com.rookie.demo.common.rxjava.RxJavaPlugin
import com.rookie.demo.common.rxjava.SimpleObserver

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class OtherThemeViewModel : BaseViewModel() {

    private val getTheme: MutableLiveData<GetThemeResponse> = MutableLiveData()

    fun getTheme(id: Int): MutableLiveData<GetThemeResponse> {
        Api.getThemeResponse(id)
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : SimpleObserver<GetThemeResponse>(this) {
                    override fun onNext(t: GetThemeResponse) {
                        getTheme.value = t
                    }
                })
        return getTheme
    }
}