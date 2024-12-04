package com.imaginato.homeworkmvvm.data.local.login

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imaginato.homeworkmvvm.data.remote.login.response.Data

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class LogInDatabase : RoomDatabase() {
    abstract val logIn: LogInDao
}