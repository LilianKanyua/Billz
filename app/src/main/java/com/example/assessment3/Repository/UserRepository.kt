package com.example.assessment3.Repository

import com.example.assessment3.Api.ApiClient
import kotlinx.coroutines.Dispatchers
import com.example.assessment3.Api.ApiInterface
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
}