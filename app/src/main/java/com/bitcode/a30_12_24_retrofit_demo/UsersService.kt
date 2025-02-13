package com.bitcode.a30_12_24_retrofit_demo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersService {

    @GET("api/users/{user_id}")
    suspend fun fetchUsers(@Path("user_id") id : Int) : User

    //anonymous companion object
    companion object{
        fun getInstance() : UsersService{
            val retrofit = Retrofit.Builder()
                val usersService = retrofit.baseUrl("https://reqres.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UsersService::class.java)

            return usersService
        }
    }
}