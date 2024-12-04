package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.request.LogInRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LogInDataRepository constructor(
    private var api: LogInApi
) : LoginRepository {
    companion object {
        private const val URL_LOGIN = "http://private-222d3-homework5.apiary-mock.com/api/login"
    }

    override suspend fun getLoginData(logInRequest: LogInRequest) = flow {
        val response = api.getLogInDataAsync(URL_LOGIN, logInRequest).await()
        if (response.errorCode == "00") {
            emit(response)
        } else {
            throw Exception(response.errorMessage)
        }
    }.flowOn(Dispatchers.IO)
}