package com.alertrack.alerchat

import com.google.gson.annotations.SerializedName

data class Login (
    @SerializedName("login")
    val login : String,
    @SerializedName("senha")
    val senha : String
)