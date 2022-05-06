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
        val retrofit = Retrofit.getRetrofit("http://alertrack.com.br")

        val ApiInterface = retrofit.create(ApiInterface::class.java)
        val login = Login(login = "meulogin", senha = "123" )
        val callback = ApiInterface.createPost(login)

        callback.enqueue(object: Callback<Auth> {
            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                var authResponse = response.body()
                txt_texto.text = authResponse.toString()
            }

            override fun onFailure(call: Call<Auth>, t: Throwable) {
                Toast.makeText(applicationContext,"onFailure called: "+t.message,Toast.LENGTH_SHORT).show()
                callback.cancel()
            }
        })
    }
}
