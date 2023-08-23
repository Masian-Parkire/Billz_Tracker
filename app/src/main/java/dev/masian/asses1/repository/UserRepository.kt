package dev.masian.asses1.repository

import dev.masian.asses1.api.ApiClient
import dev.masian.asses1.api.ApiInterface
import dev.masian.asses1.model.LoginRequest
import dev.masian.asses1.model.LoginResponse
import dev.masian.asses1.model.RegisterRequest
import dev.masian.asses1.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient =ApiClient.buildClient(ApiInterface::class.java)

    suspend fun register(registerRequest: RegisterRequest):Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            apiClient.loginUser(loginRequest)
        }
    }
}

