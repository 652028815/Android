package com.rookie.news.ui.main

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
class NewsAdapter(private val list: MutableList<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        val item = list[position]
        view.tv_title.text = item.title
        view.tv_tips.text = item.source
        if (item.thumbnail_img.isNotEmpty()) {
            view.iv.visibility = View.VISIBLE
            GlideApp.with(view).load(item.thumbnail_img[0]).into(view.iv)
        } else {
            view.iv.visibility = View.GONE
        }
    }

    fun refresh(list: List<News>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}