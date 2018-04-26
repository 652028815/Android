package com.rookie.demo.pojo.response

import android.os.Parcel
import android.os.Parcelable
import com.rookie.demo.pojo.bean.BaseResponse
import com.rookie.demo.pojo.bean.LastThemeStory
import com.rookie.demo.pojo.bean.LastThemeTopStory

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class GetLastThemeResponse() : BaseResponse(), Parcelable {
    val stories: List<LastThemeStory>? = null

    val top_stories: List<LastThemeTopStory>? = null

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GetLastThemeResponse> = object : Parcelable.Creator<GetLastThemeResponse> {
            override fun createFromParcel(source: Parcel): GetLastThemeResponse = GetLastThemeResponse(source)
            override fun newArray(size: Int): Array<GetLastThemeResponse?> = arrayOfNulls(size)
        }
    }
}