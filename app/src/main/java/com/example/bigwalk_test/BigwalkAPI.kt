package com.example.bigwalk_test

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

public interface BigwalkAPI {
    @Headers("X-AUTH-TOKEN: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxODUiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjExNTYzMzgxLCJleHAiOjE3MDYxNzEzODF9._4DPRRFx09yIBVLqwbTGVSuP6vy5fM4UP3vJXszfP4w")
    @GET("api/campaigns/category/0/story")
    fun getData(
        @Query("page")page:Int,
        @Query("size")size:Int
    ): Call<List<APIData>>
}