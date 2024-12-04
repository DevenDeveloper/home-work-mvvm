package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.request.LogInRequest
import com.imaginato.homeworkmvvm.data.remote.login.response.LogInResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface LogInApi {
    @POST
    fun getLogInDataAsync(@Url url: String, @Body requestBody: LogInRequest): Deferred<LogInResponse>
}