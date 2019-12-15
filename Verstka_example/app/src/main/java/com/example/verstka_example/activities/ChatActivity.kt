package com.example.verstka_example.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.verstka_example.R
import com.example.verstka_example.innerModels.GetMessagesResponse
import com.example.verstka_example.innerModels.Message
import com.example.verstka_example.network.SocketManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.chat.*

class ChatActivity : AppCompatActivity() {

    private val socketManager: SocketManager = SocketManager(this, { message ->
        runOnUiThread {
            if (message != null) {
                getMessages()
            }
        }

    }, { messages ->
        runOnUiThread {
            if (messages != null) {
                val response: GetMessagesResponse =
                    Gson().fromJson(messages, GetMessagesResponse::class.java)
                response.items?.let { showMessages(it) }
            }
        }
    })

    private fun showMessages(messages: List<Message>) {
        tv_chat.text = ""
        for (message: Message in messages) {
            tv_chat.text = tv_chat.text.toString() + "\n" + message.user + ": " + message.message
        }
    }

    private fun getMessages() = socketManager.getMessages(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat)
        socketManager.initSocketManager()
        btn_send_message.setOnClickListener {
            socketManager.sendMessage(et_message_text.text.toString())
        }
    }
}