package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.local.login.LogInDatabase
import com.imaginato.homeworkmvvm.data.remote.login.LoginRepository
import com.imaginato.homeworkmvvm.data.remote.login.request.LogInRequest
import com.imaginato.homeworkmvvm.data.remote.login.response.Data
import com.imaginato.homeworkmvvm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject

@KoinApiExtension
class LoginActivityViewModel : BaseViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val repository: LoginRepository by inject()
    private var _resultLiveData: MutableLiveData<Data> = MutableLiveData()
    private var _progress: MutableLiveData<Boolean> = MutableLiveData()
    private val database: LogInDatabase by inject()
    val progress: LiveData<Boolean>
        get() {
            return _progress
        }

    val resultLiveData: LiveData<Data>
        get() {
            return _resultLiveData
        }

    fun getLogIn() {
        viewModelScope.launch {
            repository.getLoginData(
                LogInRequest(
                    username = username.value,
                    password = password.value
                )
            )
                .onStart {
                    _progress.value = true
                }.catch {
                    _progress.value = false
                }
                .onCompletion {
                }.collect {
                    _progress.value = false
                    _resultLiveData.value = it.data
                    it.data?.let { it1 ->
                        saveDataToRoom(it1)
                    }
                }
        }
    }

    private suspend fun saveDataToRoom(userData: Data) {
        withContext(Dispatchers.IO) {
            database.logIn.insertUser(userData)
        }
    }
    val allUsers: LiveData<List<Data>> = database.logIn.getUser()
}