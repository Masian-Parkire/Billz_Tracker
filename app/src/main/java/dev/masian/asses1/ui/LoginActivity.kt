package dev.masian.asses1.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.masian.asses1.R
import dev.masian.asses1.databinding.ActivityLoginBinding
import dev.masian.asses1.model.LoginRequest
import dev.masian.asses1.model.LoginResponse
import dev.masian.asses1.model.RegisterRequest
import dev.masian.asses1.utils.Constants
import dev.masian.asses1.viewmodel.UserViewModel
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignUp.setOnClickListener {
            validateLogin()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
        binding.btnLogin.setOnClickListener { startActivity(Intent(this,HomeActivity::class.java)) }
        binding.tvSignUp.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }


        userViewModel.loginLiveData.observe(this, Observer { loginResponse ->

            persistLogin(loginResponse)
            Toast.makeText(this, loginResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HomeActivity::class.java))

        })

//        startActivity(Intent(this, LogIn::class.java))

        userViewModel.errLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()

        })
    }

    private fun validateLogin() {
        val email = binding.etEmails.text.toString().trim()
        val password = binding.etpass.text.toString().trim()
        var error = false

        if (email.isEmpty()) {
            binding.tilEmails.error = getString(R.string.email_is_required)
            error = true
        } else {
            binding.tilpass.error = null
        }

        if (password.isEmpty()) {
            binding.tilpass.error = getString(R.string.password_is_required)
            error = true
        } else {
            binding.tilpass.error = null
        }

        if (!error) {
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
            userViewModel.loginUser(loginRequest)
        }
    }

    fun persistLogin(loginResponse: LoginResponse){
        val sharedPrefs =getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)
        val editor= sharedPrefs.edit()
        editor.putString(Constants.USER_ID, loginResponse.userId)
        editor.putString(Constants.ACCESS_TOKEN, loginResponse.accessToken)
        editor.apply()
    }
}