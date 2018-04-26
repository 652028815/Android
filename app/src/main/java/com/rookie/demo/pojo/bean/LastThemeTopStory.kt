package com.rookie.demo.pojo.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class LastThemeTopStory() : Parcelable {
    val id: Int? = null

    val type: Int? = null

    val title: String? = null

    val ga_prefix: String? = null

    val image: String? = null

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LastThemeTopStory> = object : Parcelable.Creator<LastThemeTopStory> {
            override fun createFromParcel(source: Parcel): LastThemeTopStory = LastThemeTopStory(source)
            override fun newArray(size: Int): Array<LastThemeTopStory?> = arrayOfNulls(size)
        }
    }
}