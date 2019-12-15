package com.example.verstka_example.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.verstka_example.R
import com.example.verstka_example.innerModels.*
import com.example.verstka_example.network.AuthApi
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.auth.*
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.*

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth)
        btn_login.setOnClickListener {
            login()
        }
    }

    @SuppressLint("CheckResult")
    fun login() {
        val userName = et_name.text.toString()
        val deviceId = et_deviceId.text.toString()
        val device = Device(userName, deviceId)

        Retrofit.Builder()
            .baseUrl("https://backend-chat.cloud.technokratos.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(AuthApi::class.java)
            .login(device)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t ->
                if (t.status == "ok") {
                    getSharedPreferences("SHARED_PREFERENCES", MODE_PRIVATE)
                        .edit()
                        .putString("user_name", userName)
                        .putString("device_id", deviceId)
                        .apply()
                    openChatActivity()
                }
            }, {
                it.printStackTrace()
            })
    }

    private fun openChatActivity() {
        startActivity(Intent(this, ChatActivity::class.java))
    }
}