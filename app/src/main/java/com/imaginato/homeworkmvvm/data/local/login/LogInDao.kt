package com.imaginato.homeworkmvvm.data.local.login

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imaginato.homeworkmvvm.data.remote.login.response.Data

@Dao
interface LogInDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: Data)

    @Query("SELECT * FROM User")
    fun getUser(): LiveData<List<Data>>

    @Query("DELETE FROM User")
    fun clearUserTable()
}