package com.itis.group11801.fedotova.mvpexample.data.repository

import com.crashlytics.android.Crashlytics
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val phoneAuthProvider: PhoneAuthProvider,
    private val crashlytics: Crashlytics,
) : UserRepository {

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override suspend fun signUpEmailAndPassword(email: String, password: String) =
        try {
            auth.createUserWithEmailAndPassword(email, password)
            true
        } catch (e: FirebaseException) {
            false
        }


    override fun signOut() {
        auth.signOut()
    }

    override suspend fun signInEmail(email: String, password: String) =
        withContext(Dispatchers.IO) {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                true
            } catch (e: FirebaseException) {
                false
            }
        }

    override suspend fun signInPhone(
        phoneNumber: String,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) = phoneAuthProvider.verifyPhoneNumber(
        phoneNumber,
        60,
        TimeUnit.SECONDS,
        TaskExecutors.MAIN_THREAD,
        callbacks
    )

    override suspend fun signInGoogle(credential: AuthCredential) =
        withContext(Dispatchers.IO) {
            try {
                auth.signInWithCredential(credential).await()
                true
            } catch (e: FirebaseException) {
                false
            }
        }

    override suspend fun resetPassword(email: String) =
        withContext(Dispatchers.IO) {
            try {
                auth.sendPasswordResetEmail(email).await()
                true
            } catch (e: FirebaseException) {
                false
            }
        }

    override suspend fun signInWithCredential(credential: AuthCredential) =
        try {
            auth.signInWithCredential(credential).await()
            true
        } catch (e: FirebaseException) {
            false
        }

    override fun crash() {
        crashlytics.crash()
    }
}
