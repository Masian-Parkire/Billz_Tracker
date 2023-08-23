package dev.masian.asses1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.masian.asses1.model.LoginRequest
import dev.masian.asses1.model.LoginResponse
import dev.masian.asses1.model.RegisterRequest
import dev.masian.asses1.model.RegisterResponse
import dev.masian.asses1.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val userRepo = UserRepository()
    val regLiveData = MutableLiveData<RegisterResponse>()
    val loginLiveData = MutableLiveData<LoginResponse>()
    val errLiveData = MutableLiveData<String>()

    // Function to invoke the register user
    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepo.register(registerRequest)
            if (response.isSuccessful) {
                regLiveData.postValue(response.body())
            } else {
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = userRepo.login(loginRequest)
            if (response.isSuccessful) {
                loginLiveData.postValue(response.body())
            } else {
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

