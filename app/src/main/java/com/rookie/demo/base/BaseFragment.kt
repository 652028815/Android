package com.rookie.demo.base

import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

/**
 * Author: FK
 * Date：  2018/4/25.
 */
open class BaseFragment : Fragment() {
    protected fun getSupportActionBar(): ActionBar? {
        return (activity as AppCompatActivity).supportActionBar
    }

    protected fun setTitle(title: String) {
        getSupportActionBar()?.title = title
    }
}