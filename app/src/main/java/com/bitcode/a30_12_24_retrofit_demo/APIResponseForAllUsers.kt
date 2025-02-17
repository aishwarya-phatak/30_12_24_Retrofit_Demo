package com.bitcode.a30_12_24_retrofit_demo

import com.google.gson.annotations.SerializedName

data class APIResponseForAllUsers(
    var page : Int,
    @SerializedName("per_page")
    var perPage : Int,
    @SerializedName("total")
    var total : Int,
    @SerializedName("total_pages")
    var totalPages : Int,
    @SerializedName("data")
    var users : ArrayList<User1>,
    var support1: Support1
)

data class User1(
    var id : Int,
    var email : String,
    @SerializedName("first_name")
    var firstName : String,
    @SerializedName("last_name")
    var lastName : String,
    var avatar : String
)

data class Support1(
    var url : String,
    var text : String
)