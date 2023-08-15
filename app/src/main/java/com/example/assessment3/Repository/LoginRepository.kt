package com.example.assessment3.Repository

import com.example.assessment3.Api.ApiClient
import com.example.assessment3.Api.ApiInterface
import com.example.assessment3.model.LoginRequest
import com.example.assessment3.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    private val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            apiClient.login(loginRequest)
        }
    }
}