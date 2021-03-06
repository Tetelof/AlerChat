package com.alertrack.alerchat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.alertrack.alerchat.retrofit.Autenticacao
import com.alertrack.alerchat.retrofit.Login
import com.alertrack.alerchat.retrofit.UserX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPage : AppCompatActivity() {

    private lateinit var txtLogin : EditText
    private lateinit var txtSenha : EditText
    private lateinit var buttonLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        supportActionBar?.hide()

        txtLogin = findViewById(R.id.txt_login)
        txtSenha = findViewById(R.id.txt_senha)
        buttonLogin = findViewById(R.id.button_login)

        buttonLogin.setOnClickListener{
            val login = txtLogin.text.toString()
            val senha = txtSenha.text.toString()
            fazerLogin(login, senha, "dev")
        }

    }
    fun fazerLogin(login : String, senha : String) {
        val retrofit = RetrofitService.getRetrofitInstance()

        val login = Login(login = login, senha = senha)

        val post = retrofit.createPost(login)

        post.enqueue(object: Callback<Autenticacao> {
            override fun onResponse(call: Call<Autenticacao>, response: Response<Autenticacao>) {
                if(response.isSuccessful){
                    val intent = Intent(this@LoginPage, Home::class.java)
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

    // j?? que a API nao ta funcionando, vou fazer manualmente por enquanto
    fun fazerLogin(login : String, senha : String, dev: String) {
        val login = Login(login = login, senha = senha)

        if (login.login == "meulogin" && login.senha == "123"){
            val user = UserX(
                name = "Meu nome",
                email = "meuemail@alertrack.com.br",
                avatar = "http://www.alertrack.com.br/api/teste_mobile/img/perfil_.png"
            )
            val autenticacao = Autenticacao(
                status = true,
                message = "Autenticado com sucesso",
                token = "9bdf52a4830779a1383ac24f1b3ed054",
                user = user
            )
            val sharedPref = getSharedPreferences("LoggedIn", Context.MODE_PRIVATE)
            val prefEditor = sharedPref.edit()
            prefEditor.putString("login", login.login)
            prefEditor.putString("senha", login.senha)
            prefEditor.putBoolean("alreadyLogged", true)
            prefEditor.putString("token",autenticacao.token)
            prefEditor.commit()

            val message_intent = Intent(this, Home::class.java)
            intent.putExtra("token", autenticacao.token)
            finish()
            startActivity(message_intent)
        }else{
            Toast.makeText(baseContext, "Usu??rio ou senha incorretos!", Toast.LENGTH_SHORT).show()
        }
    }
}