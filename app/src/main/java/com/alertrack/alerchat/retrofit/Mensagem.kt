package com.alertrack.alerchat.retrofit

data class Mensagem(
    var id : Int,
    var from_me : Boolean,
    var message : String,
    var timestamp: Int
)
