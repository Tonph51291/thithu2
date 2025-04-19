package com.example.thithu2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MeoService {
    @GET("meo")
    suspend fun getMeo () : Response<List<MeoRespone>>
    @GET("meo/{id}")
    suspend fun getMeoById (@Path("id") id : String) : Response<MeoRespone>


}