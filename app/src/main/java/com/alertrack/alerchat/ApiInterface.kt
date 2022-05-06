package com.alertrack.alerchat

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @POST("/api/teste_mobile/auth.php")
    fun createPost(@Body login: Login) : Call<Autenticacao>
    
    companion object{
        private val retrofitService : RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://alertrack.com.br/")
                .addConverterFactory(GsonConverteractory.create())
                .build()
            retrofit.create(RetrofitService::class.java)
        }
        fun getRetrofitInstance() : RetrofitService {
            return retrofitService
        }
    }
}
