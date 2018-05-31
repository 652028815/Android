package com.rookie.news.ui.main

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rookie.news.R
import com.rookie.news.common.glide.GlideApp
import com.rookie.news.pojo.response.News
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Author: FK
 * Date：  2018/5/28.
 */
class NewsAdapter : PagedListAdapter<News, NewsAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News?, newItem: News?): Boolean {
                return oldItem?.news_id == newItem?.news_id
            }

            //比较内容是否发生变更，==等同于 equals(),需要重写 @see News.equals(),否则相当于===
            override fun areContentsTheSame(oldItem: News?, newItem: News?): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        getItem(position)?.let { item ->
            view.tv_title.text = item.title
            view.tv_tips.text = item.source
            if (item.thumbnail_img.isNotEmpty()) {
                view.iv.visibility = View.VISIBLE
                GlideApp.with(view).load(item.thumbnail_img[0]).into(view.iv)
            } else {
                view.iv.visibility = View.GONE
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}