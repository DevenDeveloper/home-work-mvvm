package com.imaginato.homeworkmvvm.data.remote.login.response

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User")
data class Data(
    @PrimaryKey
    var id: Int? = 0,
    @SerializedName("userId") var userId: String? = null,
    @SerializedName("userName") var userName: String? = null,
    @SerializedName("isDeleted") var isDeleted: Boolean? = null
)