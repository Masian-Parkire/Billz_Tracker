package dev.masian.asses1.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    var email:String,
    var password:String
)
