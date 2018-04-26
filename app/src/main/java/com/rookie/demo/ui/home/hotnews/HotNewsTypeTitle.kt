package com.rookie.demo.ui.home.hotnews

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_last_title.view.*

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class HotNewsTypeTitle(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(str: String) {
        view.title.text = str
    }
}