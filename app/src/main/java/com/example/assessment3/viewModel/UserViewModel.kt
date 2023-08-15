package com.example.assessment3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment3.Repository.UserRepository
import com.example.assessment3.model.RegisterRequest
import com.example.assessment3.model.RegisterResponse
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val userRepository = UserRepository()
    val registerData = MutableLiveData<RegisterResponse>()
    val errorLiveData = MutableLiveData<String>()

    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.registration(registerRequest)
            registerData.postValue(response.body())
            if (response.isSuccessful) {
                registerData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}
