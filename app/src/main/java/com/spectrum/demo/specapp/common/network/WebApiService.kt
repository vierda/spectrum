package com.spectrum.demo.specapp.common.network

import com.spectrum.demo.specapp.common.data.model.Company
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebApiService {

    companion object {

        private const val protocol = "https"
        private const val serverName = "next.json-generator.com"

        fun getHost(): String {
            val builder = StringBuilder(1024)

            builder.append(protocol)
                    .append("://")
                    .append(serverName)

            return builder.toString()
        }

        fun create(): WebApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(getHost())
                    .build()

            return retrofit.create(WebApiService::class.java)
        }
    }

    @GET("/api/json/get/Vk-LhK44U")
    fun loadAllReport(): Call<List<Company>>
}