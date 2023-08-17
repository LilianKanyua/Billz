package com.example.assessment3.Repository

import com.example.assessment3.Api.ApiClient
import kotlinx.coroutines.Dispatchers
import com.example.assessment3.Api.ApiInterface
import com.example.assessment3.model.LoginRequest
import com.example.assessment3.model.LoginResponse
import com.example.assessment3.model.RegisterRequest
import com.example.assessment3.model.RegisterResponse
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
     val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun registration(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return withContext(Dispatchers.IO) {
            apiClient.registration(registerRequest)
        }
    }
    suspend fun login(logInRequest: LoginRequest):Response<LoginResponse>{
        return withContext(Dispatchers.IO){
            apiClient.login(logInRequest)
//            apiClient.loginUser(loginRequest)
        }
    }
}