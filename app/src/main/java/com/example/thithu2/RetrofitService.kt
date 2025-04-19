package com.example.thithu2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitService {
    private val retrofit : Retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:3000/").addConverterFactory(GsonConverterFactory.create()).build()

    val meoService : MeoService = retrofit.create(MeoService::class.java)
}