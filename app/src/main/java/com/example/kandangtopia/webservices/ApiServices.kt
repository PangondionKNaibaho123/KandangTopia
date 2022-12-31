package com.example.kandangtopia.webservices

import com.example.kandangtopia.model.Kandang
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("mhmdJalal/json-sample/kandang_aktif")
    suspend fun getlistkandangaktif(): List<Kandang>

    @GET("mhmdJalal/json-sample/kandang_rehat")
    suspend fun getlistkandangrehat(): List<Kandang>
}