package com.rookie.news.base

import android.os.Bundle
import android.view.View

/**
 * Author: FK
 * Date：  2018/5/29.
 */
abstract class LazyFragment : BaseFragment() {
    abstract fun lazyLoadData()
}