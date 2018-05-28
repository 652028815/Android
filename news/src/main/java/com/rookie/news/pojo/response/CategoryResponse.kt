package com.rookie.news.pojo.response

import android.os.Parcel
import android.os.Parcelable

/**
 * Author: FK
 * Date：  2018/5/25.
 */
data class Data(
    val categories: List<Category>
)

data class Category(
        val name: String,
        val alias: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(alias)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Category> = object : Parcelable.Creator<Category> {
            override fun createFromParcel(source: Parcel): Category = Category(source)
            override fun newArray(size: Int): Array<Category?> = arrayOfNulls(size)
        }
    }
}