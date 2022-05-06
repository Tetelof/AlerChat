package com.alertrack.alerchat

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit {
    companion object{
        fun getRetrofit(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}