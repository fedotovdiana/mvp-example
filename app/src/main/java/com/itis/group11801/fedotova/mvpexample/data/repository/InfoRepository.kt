package com.itis.group11801.fedotova.mvpexample.data.repository

import com.google.firebase.database.ValueEventListener

interface InfoRepository {

    suspend fun insertInfo(info: String)

    fun setListener(listener: ValueEventListener)
}
