package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.request.LogInRequest
import com.imaginato.homeworkmvvm.data.remote.login.response.LogInResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun getLoginData(logInRequest: LogInRequest): Flow<LogInResponse>
}