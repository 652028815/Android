package com.rookie.news.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rookie.news.pojo.bean.NetWorkState

/**
 * Author: FK
 * Date：  2018/4/24.
 */
open class BaseViewModel : ViewModel() {
    val netWorkState: MutableLiveData<NetWorkState> = MutableLiveData()
}