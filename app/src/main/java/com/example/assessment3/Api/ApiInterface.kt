package com.example.assessment3.Api

import com.example.assessment3.model.LoginRequest
import com.example.assessment3.model.LoginResponse
import com.example.assessment3.model.RegisterRequest
import com.example.assessment3.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
    suspend fun registration(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/users/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>
}
