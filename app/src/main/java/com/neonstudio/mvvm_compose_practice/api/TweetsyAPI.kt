package com.neonstudio.mvvmprictice2.api

import com.neonstudio.mvvmprictice2.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface TweetsyAPI {

    @GET("/v3/b/674848cbad19ca34f8d1eba3?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String) : Response<List<TweetListItem>>

    @GET("/v3/b/674848cbad19ca34f8d1eba3?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories() : Response<List<String>>

}