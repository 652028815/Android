package com.rookie.demo.ui.home.hotnews

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.rookie.demo.base.BaseViewModel
import com.rookie.demo.common.api.Api
import com.rookie.demo.pojo.response.GetLastThemeResponse
import com.rookie.demo.common.rxjava.RxJavaPlugin
import com.rookie.demo.common.rxjava.SimpleObserver
import com.rookie.demo.pojo.bean.LastThemeStory
import com.rookie.demo.repository.HotNewsRepository

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class HotNewsViewModel : BaseViewModel() {

    private val lastTheme: MutableLiveData<GetLastThemeResponse> = MutableLiveData()
    private val respository = HotNewsRepository()

    fun getLastTheme(): MutableLiveData<GetLastThemeResponse> {
        Api.getLastTheme()
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : SimpleObserver<GetLastThemeResponse>(this) {
                    override fun onNext(t: GetLastThemeResponse) {
                        lastTheme.value = t
                    }
                })
        return lastTheme
    }

    fun getLashThemeStory(): LiveData<PagedList<LastThemeStory>> {
        return respository.post()
    }
}