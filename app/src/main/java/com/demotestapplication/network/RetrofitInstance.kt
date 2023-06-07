package com.demotestapplication.network

import com.demotestapplication.base.ConfigURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object{
        fun getRetroInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(ConfigURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HTTPLogger.getLogger())
                .build()
        }

    }
}