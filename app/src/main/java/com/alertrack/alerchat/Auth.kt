package com.alertrack.alerchat

import com.google.gson.annotations.SerializedName

abstract class Auth {

    abstract val status: Boolean

    abstract val message: String

    abstract val token: String

    abstract val user: User
}
