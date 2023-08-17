package com.example.assessment3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment3.Repository.UserRepository
import com.example.assessment3.model.LoginRequest
import com.example.assessment3.model.LoginResponse
import kotlinx.coroutines.launch
import com.example.assessment3.model.RegisterRequest
import com.example.assessment3.model.RegisterResponse


class UserViewModel : ViewModel() {
    val userRepo = UserRepository()
    val regiLiveData = MutableLiveData<RegisterResponse>()
    val logLiveData = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepo.registration(registerRequest)
//            val response = userRepo.register(registerRequest)
            if (response.isSuccessful){
                regiLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }


    fun logInUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepo.login(loginRequest)
//            val response = userRepo.login(logInRequest)
            if (response.isSuccessful){
                logLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }


}

