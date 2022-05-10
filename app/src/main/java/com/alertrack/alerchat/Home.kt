package com.alertrack.alerchat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alertrack.alerchat.retrofit.Chat
import com.alertrack.alerchat.retrofit.Contato
import com.alertrack.alerchat.retrofit.ChatResposta
import com.alertrack.alerchat.retrofit.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<Chat>
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensagens)


        val token = Token(token=intent.getStringExtra("token").toString())

        userList = ArrayList()
        adapter = UserAdapter(this, userList)

        userRecyclerView = findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter

        for (chat in getChats()){
            userList.add(chat)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            val intent = Intent(this@Home, MainActivity::class.java)

            val sharedPrefs = getSharedPreferences("LoggedIn", Context.MODE_PRIVATE)
            var edit = sharedPrefs.edit()

            edit.remove("login")
            edit.remove("senha")
            edit.remove("alreadyLogged")
            edit.remove("token")
            edit.commit()

            finish()
            startActivity(intent)
            return true
        }
        return true
    }

    fun getChats(token: Token) : List<Chat>{
        val retrofit = RetrofitService.getRetrofitInstance()
        val post = retrofit.createPost(token)
        var listaChats = emptyList<Chat>()

        post.enqueue(object: Callback<ChatResposta> {
            override fun onResponse(call: Call<ChatResposta>, response: Response<ChatResposta>) {
                if(response.isSuccessful){
                    listaChats = response.body()!!.chat
                }else {
                    Log.d("TAG", "onResponse: " + response.message())
                }
            }

            override fun onFailure(call: Call<ChatResposta>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
        return listaChats
    }

    // Hardcode da response do API que não esta funcionando
    fun getChats() : ArrayList<Chat>{
        var listaChats = ArrayList<Chat>()

        for (i in 0..10){
            listaChats.add(Chat(
                "mensagem $i",
                Contato(
                    i,
                    "Contato $i",
                    "http://www.alertrack.com.br/api/teste_mobile/img/perfil1_.png"
                )
            ))
        }
        return listaChats
    }
}