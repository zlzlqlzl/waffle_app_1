package com.example.waffleappHW1.data.service

import com.example.waffleappHW1.data.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    fun getUsers(): Single<List<User>>
}