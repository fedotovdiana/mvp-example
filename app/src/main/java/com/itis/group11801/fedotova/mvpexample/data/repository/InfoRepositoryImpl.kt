package com.itis.group11801.fedotova.mvpexample.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.itis.group11801.fedotova.mvpexample.data.model.Info
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InfoRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : InfoRepository {

    override suspend fun insertInfo(info: String) {
        auth.currentUser?.uid?.let { uid ->
            val item = Info(UUID.randomUUID().toString(), uid, info)
            database.getReference(TABLE).child(uid).child(item.id).setValue(item)
        }
    }

    override fun setListener(listener: ValueEventListener) {
        auth.currentUser?.uid?.let { uid ->
            database.getReference(TABLE).child(uid).addValueEventListener(listener)
        }
    }

    companion object {
        const val TABLE = "info"
    }
}
