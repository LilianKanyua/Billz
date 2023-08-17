package com.example.assessment3.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.assessment3.databinding.ActivityMainBinding
import com.example.assessment3.model.RegisterRequest

import com.example.assessment3.utils.Constants
import com.example.assessment3.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        redirectUser()
    }

    override fun onResume() {
        super.onResume()
        binding.btnSignUp.setOnClickListener {
            validateRegistration()

        }
        binding.btnLogin.setOnClickListener{
          validateRegistration()
        }
        userViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE
        })

       userViewModel.regiLiveData.observe(this, Observer { regResponse ->
           binding.pbRegister.visibility = View.GONE
           Toast.makeText(this, regResponse.message, Toast.LENGTH_LONG).show()

      })
    }

    fun validateRegistration() {
//      clearErrors()
        val firstname = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val password = binding.etPasswordUp.text.toString()
        val confirmPassword = binding.etConfim.text.toString()
        var error = false

        if (firstname.isBlank()) {
            binding.tilFirstName.error = "your username is required"
            error = true
        }

        if (lastName.isBlank()) {
            binding.tilLastName.error = "Your last name is required"
            error = true
        }

        if (email.isBlank()) {
            binding.tilEmail.error = "You email is required"
            error = true
        }

        if (phoneNumber.isBlank()) {
            binding.tilphoneNumber.error = "Your phone number is required"
            error = true
        }

        if (password.isBlank()) {
            binding.tilPassword.error = "Your password is required"
            error = true
        }

        if (confirmPassword != password) {
            binding.tilConfirm.error = "Your email does not match"
            error = true
        }

        if (!error) {
            val registerRequest = RegisterRequest(
                first_name = firstname,
                last_name  = lastName,
                email= email,
                phone_number = phoneNumber,
                password = password,

            )

            binding.pbRegister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }
    }
    fun clearErrors() {
        binding.tilFirstName.error = null
        binding.tilLastName.error = null
        binding.tilphoneNumber.error = null
        binding.tilPassword.error = null
        binding.tilConfirm.error = null
    }
    fun redirectUser(){
 val sharedPrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getString(Constants.USER_ID,Constants.EMPTY_STRING)
//        val userId = sharedPrefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)
        if( userId.isNullOrBlank()){
      startActivity(Intent(this,LoginActivity::class.java))
        }
else{
    startActivity(Intent(this,HomeActivity::class.java))
        }

   }
}
