package com.rookie.demo.common.api

import com.rookie.demo.pojo.response.GetAllThemesResponse
import com.rookie.demo.pojo.response.GetLastThemeResponse
import com.rookie.demo.pojo.response.GetStartInfoResponse
import com.rookie.demo.pojo.response.GetThemeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author: FK
 * Date：  2018/4/24.
 */
interface ZhihuService {
    // 1.启动界面图像获取
    @GET("api/4/start-image/{width}*{height}")
    fun getStartInfo(@Path("width") width: Int, @Path("height") height: Int): Observable<GetStartInfoResponse>

    // 2.主题日报列表查看
    @GET("api/4/themes")
    fun getAllThemes(): Observable<GetAllThemesResponse>

    // 3.最新消息
    @GET("api/4/news/latest")
    fun getLastTheme(): Observable<GetLastThemeResponse>

    @GET("api/4/theme/{id}")
    fun getThemeResponse(@Path("id") id: Int): Observable<GetThemeResponse>
}