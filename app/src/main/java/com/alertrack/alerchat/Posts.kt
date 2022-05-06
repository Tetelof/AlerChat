package com.alertrack.alerchat

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("id")
    val id : Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("body")
    val body : String
)
