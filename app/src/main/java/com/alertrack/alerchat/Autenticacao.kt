package com.alertrack.alerchat

import com.google.gson.annotations.SerializedName

data class Autenticacao (
    val message: String,
    val status: Boolean,
    val token: String,
    val user: UserX
)
