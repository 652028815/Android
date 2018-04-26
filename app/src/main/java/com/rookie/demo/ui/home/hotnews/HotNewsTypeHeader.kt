package com.rookie.demo.ui.home.hotnews

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.rookie.demo.pojo.bean.LastThemeTopStory
import kotlinx.android.synthetic.main.item_last_header.view.*
import java.util.*

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class HotNewsTypeHeader(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(tops: List<LastThemeTopStory>?) {
        val topsLocal = ArrayList<LastThemeTopStory>()
        if (tops != null) topsLocal.run {
            add(tops.last())
            addAll(tops)
            add(tops.first())
        }

        view.view_pager.adapter = TopItemAdapter(topsLocal)

        view.view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            var currentPosition: Int = 0
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == topsLocal.size - 1) {
                    // 设置当前值为1
                    currentPosition = 1
                } else if (position == 0) {
                    // 如果索引值为0了,就设置索引值为倒数第二个
                    currentPosition = topsLocal.size - 2
                } else {
                    currentPosition = position
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE)
                    view.view_pager.setCurrentItem(currentPosition, false)
            }

        })
    }
}