package com.example.waffleappHW1

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WebAccess {
    private val photo_service: PhotoService
    private val user_service: UserService

    init {
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create()) // json->java parsing
            .build()
        photo_service = retrofit.create(PhotoService::class.java)
        user_service = retrofit.create(UserService::class.java)
    }

    fun getPhoto(): Call<List<Photo>> = photo_service.getPhoto()
    fun getUsers(): Call<List<User>> = user_service.getUsers()
}