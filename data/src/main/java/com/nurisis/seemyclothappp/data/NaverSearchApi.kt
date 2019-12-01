package com.nurisis.seemyclothappp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverSearchApi {

    @Headers("X-Naver-Client-Id:${Constant.NAVER_CLIENT_ID}", "X-Naver-Client-Secret:${Constant.NAVER_CLIENT_SECRET}")
    @GET("/v1/search/shop.json")
    suspend fun searchCloth(
        @Query("query")query:String, // search query
        @Query("start")start:Int = 1,
        @Query("display")display:Int = 100, // number of results
        @Query("sort")sort:String = "sim" // sort = sim(default) , date, asc(by price), dsc(by price)
    ): Response<NaverSearchResult>

}