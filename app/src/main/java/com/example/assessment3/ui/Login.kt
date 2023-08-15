package com.example.assessment3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assessment3.databinding.ActivityLoginBinding
import com.example.assessment3.model.LoginResponse

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        binding.btnprevious.setOnClickListener {
            var intent=Intent(this,Home::class.java)
            startActivity(intent)
            approveLogin()
        }
    }

    fun approveLogin(){
        val username = binding.etuserName.text.toString()
        val email = binding.etEmail.text.toString()
        var error = false

        if (username.isBlank()) {
            binding.tiluserName.error = "Your username is required"
            error = true
        }

        if (email.isBlank()) {
            binding.tilEmail.error = "your email is required"
            error = true
        }

        if (!error) {
            Toast.makeText(
                this,
                " Hello $username your have successfully logged into your account ",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun clearErrors() {
        binding.tiluserName.error = null
        binding.tilEmail.error = null
    }

//    fun persistLogin(loginResponse: LoginResponse){
//        val sharedPrefs=getSharedPreferences(name:"BILLZ_PREFS",Context.MODE_PRIVATE)
//        val edition = sharedPrefs.edit()
//        editor.putString(Constants."USER_ID",loginResponse.userId)
//        editor.putString(Constants."ACCESS_TOKEN",LoginResponse)
//
//    }
}