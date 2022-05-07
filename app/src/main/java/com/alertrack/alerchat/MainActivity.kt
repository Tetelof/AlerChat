package com.alertrack.alerchat

import android.content.Intent
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

        getData()
    }

    fun getData(){
        val retrofit = RetrofitService.getRetrofitInstance()

        val login = Login(login = txt_usuario.text.toString(), senha = txt_senha.toString() )
        
        val post = retrofit.createPost(login)

        post.enqueue(object: Callback<Autenticacao> {
            override fun onResponse(call: Call<Autenticacao>, response: Response<Autenticacao>) {
                if(response.isSuccessful){
                    val intent = Intent(this@MainActivity, Mensagens::class.java)
                    startActivity(intent)
                }else {
                    Log.d("TAG", "onResponse: " + response.message())
                }
            }

            override fun onFailure(call: Call<Autenticacao>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
