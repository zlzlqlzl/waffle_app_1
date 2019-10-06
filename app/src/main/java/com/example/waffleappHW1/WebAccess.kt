package com.example.waffleappHW1

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WebAccess {
    private val photoService: PhotoService
    private val userService: UserService

    init {
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create()) // json->java parsing
            .build()
        photoService = retrofit.create(PhotoService::class.java)
        userService = retrofit.create(UserService::class.java)
    }

    fun getPhoto(): Call<List<Photo>> = photoService.getPhoto()
    fun getUsers(): Call<List<User>> = userService.getUsers()
}