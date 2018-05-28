package com.rookie.news.common.api

import com.rookie.news.pojo.response.NewsResponse
import com.rookie.news.pojo.response.Data
import com.rookie.news.pojo.response.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: FK
 * Date：  2018/4/24.
 */
interface NewsService {
    @GET("news/category")
    fun getCategories(): Observable<Response<Data>>

    @GET("news/all")
    fun getAllNews(@Query("first_id") firstId: String,
                   @Query("last_id") lastId: String,
                   @Query("size") size: String): Observable<Response<NewsResponse>>

    @GET("news/all")
    fun getAllNews(): Observable<Response<NewsResponse>>

    @GET("news/selection")
    fun getSelectionNews(): Observable<Response<NewsResponse>>

    @GET("news/hot")
    fun getHotNews(): Observable<Response<NewsResponse>>
}