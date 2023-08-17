package com.example.assessment3.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.assessment3.databinding.ActivityLoginBinding
import com.example.assessment3.model.LoginResponse
import com.example.assessment3.viewModel.UserViewModel
import com.example.assessment3.model.LoginRequest


class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginn.setOnClickListener {
            validateAndLogIn()
        }

        userViewModel.errorLiveData.observe(this, Observer { error ->
            handleError(error)
        })

        userViewModel.logLiveData.observe(this, Observer { logResponse ->
            handleSuccessfulLogin(logResponse)
        })
    }

    private fun validateAndLogIn() {
        clearErrors()

        val email = binding.etEmail.text.toString()
        val password = binding.etPasswordLog.text.toString()

        if (email.isBlank()) {
            binding.tilEmail.error = "Email Address is Required"
            return
        }

        if (password.isBlank()) {
            binding.tilPasswordLog.error = "Password is Required"
            return
        }

        val loginRequest = LoginRequest(email = email, password = password)
        binding.pBar.visibility = View.VISIBLE
        userViewModel.logInUser(loginRequest)
    }

    private fun clearErrors() {
        binding.tilEmail.error = null
        binding.tilPasswordLog.error = null
    }

    private fun handleError(errorMessage: String) {
        binding.pBar.visibility = View.GONE
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun handleSuccessfulLogin(loginResponse: LoginResponse) {
        persistLogIn(loginResponse)
        binding.pBar.visibility = View.GONE
        Toast.makeText(this, loginResponse.message, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun persistLogIn(loginResponse: LoginResponse) {
        val sharedPrefs = getSharedPreferences("BILLZ_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("USER_ID", loginResponse.user_id)
        editor.putString("ACCESS_TOKEN", loginResponse.access_token)
        editor.apply()
    }
}


