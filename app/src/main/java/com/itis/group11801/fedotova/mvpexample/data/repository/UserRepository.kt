package com.itis.group11801.fedotova.mvpexample.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider

interface UserRepository {

    fun getCurrentUser(): FirebaseUser?

    suspend fun signUpEmailAndPassword(email: String, password: String): Boolean

    fun signOut()

    suspend fun signInEmail(email: String, password: String): Boolean

    suspend fun signInPhone(
        phoneNumber: String, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    )

    suspend fun signInGoogle(credential: AuthCredential): Boolean

    suspend fun resetPassword(email: String): Boolean

    suspend fun signInWithCredential(credential: AuthCredential): Boolean

    fun crash()
}
