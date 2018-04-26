package com.rookie.demo.ui.home.hotnews

import android.support.v7.widget.RecyclerView
import android.view.View
import com.rookie.demo.common.glide.GlideApp
import com.rookie.demo.pojo.bean.LastThemeStory
import kotlinx.android.synthetic.main.item_last_item.view.*

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class HotNewsTypeItem(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    init {
        view.setOnClickListener(this)
    }

    fun bind(story: LastThemeStory) {
        itemView.tag = story

        view.tv_title.text = story.title

        story.images?.get(0).let {
            GlideApp.with(view.context)
                    .load(it)
                    .into(view.icon)
        }
    }

    override fun onClick(v: View) {
        val story = v.tag as LastThemeStory
    }
}