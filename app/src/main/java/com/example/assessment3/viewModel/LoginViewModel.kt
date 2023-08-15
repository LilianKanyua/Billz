package com.example.assessment3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment3.Repository.LoginRepository
import com.example.assessment3.model.LoginRequest
import com.example.assessment3.model.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() { // Extend ViewModel class
    private val loginRepository = LoginRepository()
    val loginData = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = loginRepository.login(loginRequest)
            if (response.isSuccessful) {
                loginData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}
