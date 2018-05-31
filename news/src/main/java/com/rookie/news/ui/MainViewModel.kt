package com.rookie.news.ui

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.MainThread
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rookie.news.api.Api
import com.rookie.news.base.BaseViewModel
import com.rookie.news.common.rxjava.NetworkObserver
import com.rookie.news.common.rxjava.RxJavaPlugin
import com.rookie.news.common.rxjava.SimpleObserver
import com.rookie.news.common.util.SpUtil
import com.rookie.news.pojo.response.Category
import com.rookie.news.pojo.response.Data
import com.rookie.news.pojo.response.Response

/**
 * Author: FK
 * Date：  2018/5/25.
 */
class MainViewModel : BaseViewModel() {
    private val TAG = "MainViewModel"
    private val categories = MutableLiveData<List<Category>>()

    @MainThread
    fun getCategories(): MutableLiveData<List<Category>> {
        val oldStr = SpUtil.getSp().getString("Categories", null)
        oldStr?.let {
            val list: List<Category> = Gson().fromJson(it, object : TypeToken<List<Category>>() {}.type)
            categories.postValue(list)
        }
        Api.getCategories()
                .compose(RxJavaPlugin.applySchedulers())
                .subscribe(object : NetworkObserver<Response<Data>>(networkState) {
                    override fun onNext(t: Response<Data>) {
                        val str = Gson().toJson(t.data.categories)
                        if (!TextUtils.equals(str, oldStr)) {
                            categories.postValue(t.data.categories)
                            SpUtil.put { editor -> editor.putString("Categories", str) }
                        }
                    }
                })
        return categories
    }
}