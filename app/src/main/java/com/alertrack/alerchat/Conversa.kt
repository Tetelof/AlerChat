package com.alertrack.alerchat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alertrack.alerchat.retrofit.*
import kotlinx.android.synthetic.main.activity_conversa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp
import java.time.LocalDateTime
import kotlin.random.Random

class Conversa : AppCompatActivity() {

    private lateinit var chatRecyclerView : RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Mensagem>
    private lateinit var contactUser: TextView
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversa)

        val name = intent.getStringExtra("name")
        val avatar = intent.getStringExtra("avatar")

        supportActionBar?.hide()

        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sendButton)
        messageList = getMensagens("dev").mensagens
        messageAdapter = MessageAdapter(this, messageList)
        contactUser= findViewById(R.id.contactUser)
        contactUser.text = name

        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter



        sendButton.setOnClickListener{
            val mensagem = messageBox.text.toString()
            val objMensagem = Mensagem(
                messageList.last().id+1,
                true,mensagem,
                timestamp = messageList.last().timestamp+60
            )
            messageAdapter.messageList.add(objMensagem)
            messageBox.setText("")
            chatRecyclerView.scrollToPosition(messageAdapter.messageList.size-1)
        }

        backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener{
            finish()
        }
    }
    fun getMensagens() : MsgResposta{
        val retrofit = RetrofitService.getRetrofitInstance()
        val get = retrofit.getMensagens()
        var msgResposta = MsgResposta(false, ArrayList())

        get.enqueue(object : Callback<MsgResposta> {
            override fun onResponse(call: Call<MsgResposta>, response: Response<MsgResposta>) {
                msgResposta = response.body()!!
            }

            override fun onFailure(call: Call<MsgResposta>, t: Throwable) {
                Toast.makeText(baseContext,"Erro: " + t.message, Toast.LENGTH_SHORT).show()
            }

        })

        return msgResposta
    }

    // hardcode da resposta do GET de MsgResposta por conta da API
    fun getMensagens(dev: String) : MsgResposta{
        var mensagens = ArrayList<Mensagem>()

        for (i in 0..10){
            mensagens.add(
                Mensagem(
                    id = i,
                    from_me = Random.nextBoolean(),
                    message = "mensagem $i",
                    timestamp = 1594736520 + (i*60)
                )
            )
        }
        return MsgResposta(status = true, mensagens = mensagens)
    }
}