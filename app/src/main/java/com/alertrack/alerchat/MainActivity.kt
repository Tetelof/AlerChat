package com.alertrack.alerchat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    fun getData(){
        val retrofit = RetrofitService.getRetrofitInstance()

        val login = Login(login = "meulogin", senha = "123" )
        
        val post = retrofit.createPost(login)

        post.enqueue(object: Callback<Autenticacao> {
            override fun onResponse(call: Call<Autenticacao>, response: Response<Autenticacao>) {
                if(response.isSuccessfuk){
                    Log.d("TAG", "onResponse: " + response..body()?.status.toString())
                }else{
                    Log.d("TAG", "onResponse: " + response.message())
            }

            override fun onFailure(call: Call<Auth>, t: Throwable) {
                Log.d("TAG", "onResponse: " + t.message())
            }
        })
    }
}
