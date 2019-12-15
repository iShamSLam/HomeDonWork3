package com.example.verstka_example.innerModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Device(
    val username: String,
    @SerializedName("device_id")
    val deviceId: String
)

data class LoginResponse(val status: String)

data class Message(
    val id: Long,
    val message: String = "",
    val user: String = ""
)

class GetMessagesResponse {

    @SerializedName("items")
    @Expose
    var items: List<Message>? = null
    @SerializedName("first")
    @Expose
    var first: Int? = null

}

