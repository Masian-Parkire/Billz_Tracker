package dev.masian.asses1.api

import dev.masian.asses1.model.LoginRequest
import dev.masian.asses1.model.LoginResponse
import dev.masian.asses1.model.RegisterRequest
import dev.masian.asses1.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest):Response<LoginResponse>


}