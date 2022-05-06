package com.alertrack.alerchat

import com.google.gson.annotations.SerializedName

data class Auth {

    val status: Boolean

    val message: String

    val token: String

    val user: UserX
}
