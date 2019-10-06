package com.example.waffleappHW1

import retrofit2.Call
import retrofit2.http.GET

interface PhotoService {
    @GET("/photos")
    fun getPhoto(): Call<List<Photo>>
}