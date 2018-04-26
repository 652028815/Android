package com.rookie.demo.pojo.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Author: FK
 * Date：  2018/4/25.
 */
class ThemeItem : Parcelable {

    /**
     * color : 16031744
     * thumbnail : http://pic2.zhimg.com/f2e97ff073e5cf9e79c7ed498727ebd6.jpg
     * description : 从业者推荐的财经金融资讯
     * id : 6
     * name : 财经日报
     */

    var color: Int = 0
    var thumbnail: String? = null
    var description: String? = null
    var id: Int = 0
    var name: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.color)
        dest.writeString(this.thumbnail)
        dest.writeString(this.description)
        dest.writeInt(this.id)
        dest.writeString(this.name)
    }

    constructor()

    private constructor(`in`: Parcel) {
        this.color = `in`.readInt()
        this.thumbnail = `in`.readString()
        this.description = `in`.readString()
        this.id = `in`.readInt()
        this.name = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<ThemeItem> = object : Parcelable.Creator<ThemeItem> {
            override fun createFromParcel(source: Parcel): ThemeItem {
                return ThemeItem(source)
            }

            override fun newArray(size: Int): Array<ThemeItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}
