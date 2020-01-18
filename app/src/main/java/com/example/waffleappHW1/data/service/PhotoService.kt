package com.example.waffleappHW1.data.service

import com.example.waffleappHW1.data.Photo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface PhotoService {
    @GET("/photos")
    fun getPhoto(): Single<List<Photo>>
}