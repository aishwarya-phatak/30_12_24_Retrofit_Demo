package com.bitcode.a30_12_24_retrofit_demo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface UsersService {

    //to fetch single user
    @GET("users/{user_id}")
    suspend fun fetchUser(@Path("user_id") id: Int): APIResponse

    //to fetch all users
    @GET("users")
    suspend fun fetchAllUsers(@Query("page") page : Int) : APIResponseForAllUsers

    //anonymous companion object
    companion object {
        fun getInstance(): UsersService {
            var usersService: UsersService? = null

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder().baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            usersService = retrofit.create(UsersService::class.java)
            return usersService!!
        }
    }
}