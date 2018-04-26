package com.rookie.demo.ui.home.hotnews

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rookie.demo.R
import com.rookie.demo.pojo.bean.LastThemeStory
import com.rookie.demo.pojo.bean.LastThemeTopStory
import com.rookie.demo.pojo.response.GetLastThemeResponse
import java.util.*

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class HotNewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = ArrayList<Any>()
    private val tops = ArrayList<LastThemeTopStory>()

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> TYPE_HEADER
            data[position] is LastThemeStory -> TYPE_ITEM
            else -> TYPE_TITLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return when (viewType) {
            TYPE_HEADER -> HotNewsTypeHeader(layoutInflater.inflate(R.layout.item_last_header, parent, false))
            TYPE_ITEM -> HotNewsTypeItem(layoutInflater.inflate(R.layout.item_last_item, parent, false))
            else -> HotNewsTypeTitle(layoutInflater.inflate(R.layout.item_last_title, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HotNewsTypeHeader -> holder.bind(tops)
            is HotNewsTypeItem -> holder.bind(data[position - 1] as LastThemeStory)
            is HotNewsTypeTitle -> holder.bind(data[position - 1] as String)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun notifyDataSetChanged(response: GetLastThemeResponse) {
        data.clear()
        response.stories?.let { data.addAll(it) }
        tops.clear()
        response.top_stories?.let { tops.addAll(it) }

        super.notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_HEADER = 1
        private const val TYPE_ITEM = 2
        private const val TYPE_TITLE = 3
    }
}