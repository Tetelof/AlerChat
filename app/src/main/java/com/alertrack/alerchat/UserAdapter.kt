package com.alertrack.alerchat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alertrack.alerchat.retrofit.Chat
import com.alertrack.alerchat.retrofit.Contato
import kotlinx.android.synthetic.main.userx_layout.view.*

class UserAdapter(val context: Context, val userList: ArrayList<Chat>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.userx_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentChat = userList[position]
        val name = currentChat.contact.name
        val avatar = currentChat.contact.avatar

        holder.txtName.text = name
        holder.txtLastMessage.text = currentChat.last_msg
        holder.avatar.text = avatar

        holder.itemView.setOnClickListener{
            val intent = Intent(context, Conversa::class.java)
            intent.putExtra("name", name)
            intent.putExtra("avatar", avatar)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.txt_Nome)
        val txtLastMessage = itemView.findViewById<TextView>(R.id.txt_lastMessage)
        val avatar = itemView.findViewById<TextView>(R.id.avatar)
    }
}