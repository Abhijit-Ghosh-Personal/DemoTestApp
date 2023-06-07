package com.demotestapplication.network

import com.demotestapplication.model.AppListResponse
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("list")
    suspend fun getApplicationData(@Field("kid_id") kid_id : String): AppListResponse
}