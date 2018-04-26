package com.rookie.demo.ui.home.hotnews

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rookie.demo.R
import com.rookie.demo.common.glide.GlideApp
import com.rookie.demo.pojo.bean.LastThemeTopStory
import kotlinx.android.synthetic.main.vp_item_hotnews.view.*

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class TopItemAdapter(val topStory: List<LastThemeTopStory>) : PagerAdapter() {

    override fun getCount(): Int {
        return topStory.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.vp_item_hotnews, container, false)
        view.tv_des.text = topStory[position].title
        GlideApp.with(container.context)
                .load(topStory[position].image)
                .into(view.iv_icon)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}