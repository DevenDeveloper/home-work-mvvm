package com.imaginato.homeworkmvvm.data.remote.login.request

import com.google.gson.annotations.SerializedName

data class LogInRequest(
    @SerializedName("username") var username: String? = null,
    @SerializedName("password") var password: String? = null
)