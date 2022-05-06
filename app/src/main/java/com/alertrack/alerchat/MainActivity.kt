package com.alertrack.alerchat

import android.os.Bundle
import android.util.Log
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
        if (button_login.isPressed) {
            getData(txt_usuario.text.toString(),txt_senha.toString())
        }
    }

    fun getData(usuario: String, senha: String) : Login{
        val retrofit = RetrofitService.getRetrofitInstance()

        val login = Login(login = usuario, senha = senha )
        
        val post = retrofit.createPost(login)

        post.enqueue(object: Callback<Autenticacao> {
            override fun onResponse(call: Call<Autenticacao>, response: Response<Autenticacao>) {
                if(response.isSuccessful){
                    Log.d("TAG", "onResponse: " + response.body()?.status.toString())
                }else {
                    Log.d("TAG", "onResponse: " + response.message())
                }
            }

            override fun onFailure(call: Call<Autenticacao>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return login
    }
}
