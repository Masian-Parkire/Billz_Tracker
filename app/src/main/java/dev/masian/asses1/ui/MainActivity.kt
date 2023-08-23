package dev.masian.asses1.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.masian.asses1.databinding.ActivityMainBinding
import dev.masian.asses1.model.RegisterRequest
import dev.masian.asses1.utils.Constants
import dev.masian.asses1.viewmodel.UserViewModel

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
                validateSignUp()
            }

            binding.tvSignIn.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
            binding.btnSignUp.setOnClickListener { startActivity(Intent(this, HomeActivity::class.java)) }

            userViewModel.regLiveData.observe(this, Observer { regResponse ->
                Toast.makeText(this, regResponse.message, Toast.LENGTH_LONG).show()
                startActivity(Intent(this,LoginActivity::class.java))
                binding.pbRegister.visibility=View.GONE
            })

//        startActivity(Intent(this, LogIn::class.java))

            userViewModel.errLiveData.observe(this, Observer { err ->
                Toast.makeText(this, err, Toast.LENGTH_LONG).show()
                binding.pbRegister.visibility=View.GONE
            })
        }


    private fun validateSignUp() {
        val firstname = binding.etFirstName.text.toString().trim()
        val lastname = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhoneNo.text.toString().trim()
        val passy = binding.etPassword.text.toString().trim()
        val confirmpass = binding.etPassConfirm.text.toString().trim()
        var error = false

        if (firstname.isEmpty()) {
            binding.tillFirstName.error = "Username is required"
            error = true
        } else {
            binding.tillFirstName.error = null
        }
        if (lastname.isEmpty()) {
            binding.tilLastName.error = "Username is required"
            error = true
        } else {
            binding.tilLastName.error = null
        }

        if (phone.isEmpty()) {
            binding.tilPhoneNo.error = "Phone Number is required"
            error = true
        } else {
            binding.tilPhoneNo.error = null
        }

        if (email.isEmpty()) {
            binding.tilEmail.error = "Email is required"
            error = true
        } else {
            binding.tilEmail.error = null
        }

        if (passy.isEmpty()) {
            binding.tillPassword.error = "Password is required"
            error = true
        } else {
            binding.tillPassword.error = null
        }

        if (confirmpass.isEmpty()) {
            binding.tilPassConfirm.error = "Password confirmation is required"
            error = true
        } else {
            binding.tilPassConfirm.error = null
        }

        if (passy != confirmpass) {
            binding.tilPassConfirm.error = "Password and confirmation do not match"
            error = true
        }

        if (!error) {
            val registerRequest = RegisterRequest(
                firstName = firstname,
                lastName = lastname,
                phoneNumber = phone,
                email = email,
                password = passy
            )
            binding.pbRegister.visibility=View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }
    }
    fun redirectUser(){
        val sharedPrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userId =sharedPrefs.getString(Constants.USER_ID,Constants.EMPTY_STRING)!!
        if (userId.isNotBlank()){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

    }
}


