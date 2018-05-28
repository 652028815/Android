package com.rookie.demo.ui.home.hotnews

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rookie.demo.R
import com.rookie.demo.pojo.bean.LastThemeStory
import com.rookie.demo.pojo.bean.LastThemeTopStory
import com.rookie.demo.pojo.bean.NetWorkState
import java.util.ArrayList

/**
 * Author: FK
 * Date：  2018/5/17.
 */
class HotNewsPagedAdapter : PagedListAdapter<LastThemeStory, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    private val data = ArrayList<Any>()
    private val tops = ArrayList<LastThemeTopStory>()
    private var networkState: NetWorkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return when (viewType) {
            HotNewsPagedAdapter.TYPE_HEADER ->
                HotNewsTypeHeader(layoutInflater.inflate(R.layout.item_last_header, parent, false))
            HotNewsPagedAdapter.TYPE_ITEM ->
                HotNewsTypeItem(layoutInflater.inflate(R.layout.item_last_item, parent, false))
            else ->
                HotNewsTypeTitle(layoutInflater.inflate(R.layout.item_last_title, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HotNewsTypeHeader -> holder.bind(tops)
            is HotNewsTypeItem -> holder.bind(data[position - 1] as LastThemeStory)
            is HotNewsTypeTitle -> holder.bind(data[position - 1] as String)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> HotNewsPagedAdapter.TYPE_HEADER
            data[position] is LastThemeStory -> HotNewsPagedAdapter.TYPE_ITEM
            else -> HotNewsPagedAdapter.TYPE_TITLE
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetWorkState.SUCCESS

    companion object {
        private const val TYPE_HEADER = 1
        private const val TYPE_ITEM = 2
        private const val TYPE_TITLE = 3

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LastThemeStory>() {
            override fun areItemsTheSame(oldItem: LastThemeStory?, newItem: LastThemeStory?): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areContentsTheSame(oldItem: LastThemeStory?, newItem: LastThemeStory?): Boolean {
                return oldItem == newItem
            }
        }
    }
}