package com.alertrack.alerchat

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api/teste_mobile/auth.php")
    fun createPost(@Body login: Login) : Call<Auth>
}