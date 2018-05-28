package com.rookie.news.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rookie.news.R
import com.rookie.news.common.glide.GlideApp
import com.rookie.news.pojo.response.News
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Author: FK
 * Date：  2018/5/28.
 */
class NewsAdapter(private val list: MutableList<News>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val item = list[position]
        view.tv_title.text = item.title
        if (item.thumbnail_img.isNotEmpty()) {
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
}