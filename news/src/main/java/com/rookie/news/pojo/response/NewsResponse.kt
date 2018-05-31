package com.rookie.news.pojo.response

import android.os.Parcel
import android.os.Parcelable

/**
 * Author: FK
 * Date：  2018/5/28.
 */

data class NewsResponse(
    val count: Int,
    val first_id: String,
    val last_id: String,
    val news: List<News>
)

data class News(
        val news_id: String,
        val title: String,
        val source: String,
        val gmt_publish: Long,
        val hot_index: Int,
        val selection: Boolean,
        val category: List<String>,
        val thumbnail_img: List<String>,
        val url: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readLong(),
            source.readInt(),
            1 == source.readInt(),
            source.createStringArrayList(),
            source.createStringArrayList(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(news_id)
        writeString(title)
        writeString(source)
        writeLong(gmt_publish)
        writeInt(hot_index)
        writeInt((if (selection) 1 else 0))
        writeStringList(category)
        writeStringList(thumbnail_img)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<News> = object : Parcelable.Creator<News> {
            override fun createFromParcel(source: Parcel): News = News(source)
            override fun newArray(size: Int): Array<News?> = arrayOfNulls(size)
        }
    }
}