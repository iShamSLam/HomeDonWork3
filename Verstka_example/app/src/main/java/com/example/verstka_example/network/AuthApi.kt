package com.example.verstka_example.network

import com.example.verstka_example.innerModels.*
import io.reactivex.Single
import retrofit2.http.*

interface AuthApi {
    @POST("login")
    fun login(@Body device: Device): Single<LoginResponse>
}