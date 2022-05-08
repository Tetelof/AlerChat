package com.alertrack.alerchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.EditText

class LoginPage : AppCompatActivity() {

    private lateinit var txtLogin : EditText
    private lateinit var txtSenha : EditText
    private lateinit var buttonLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        txtLogin = findViewById(R.id.txt_login)
        txtSenha = findViewById(R.id.txt_senha)
        buttonLogin = findViewById(R.id.button_login)

        buttonLogin.setOnClickListener{
            getData()
        }

    }
    fun getData(){
        val retrofit = RetrofitService.getRetrofitInstance()

        val login = Login(login = txtLogin.text.toString(), senha = txtSenha.text.toString() )

        val post = retrofit.createPost(login)

//        post.enqueue(object: Callback<Autenticacao> {
//            override fun onResponse(call: Call<Autenticacao>, response: Response<Autenticacao>) {
//                if(response.isSuccessful){
//                    val intent = Intent(this@MainActivity, Mensagens::class.java)
//                    startActivity(intent)
//                }else {
//                    Log.d("TAG", "onResponse: " + response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<Autenticacao>, t: Throwable) {
//                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
        // já que a API nao ta funcionando, vou fazer manualmente por enquanto
        if (login.login == "meulogin" && login.senha == "123"){
            val user = UserX(
                name = "Meu nome",
                email = "meuemail@alertrack.com.br",
                avatar = "http://www.alertrack.com.br/api/teste_mobile/img/perfil_.png"
            )
            val message_intent = Intent(this, Message::class.java)
            startActivity(message_intent)
        }
    }
}