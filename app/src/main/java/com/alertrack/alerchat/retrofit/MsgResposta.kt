package com.alertrack.alerchat.retrofit

data class MsgResposta(
    var status: Boolean,
    var mensagens: ArrayList<Mensagem>
)
