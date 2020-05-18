package com.itis.group11801.fedotova.mvpexample.ui.signin_phone

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class PhoneSignInPresenter @Inject constructor(
    private val userRepository: UserRepository
) : MvpPresenter<PhoneSignInView>() {

    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    fun verifyPhoneNumberWithCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        presenterScope.launch {
            if (withContext(Dispatchers.IO) { userRepository.signInWithCredential(credential) }) {
                viewState.showToast(AUTH_SUCCESS)
                viewState.navigateToInfo()
            } else {
                viewState.showToast(AUTH_FAILED)
            }
        }
    }

    fun signIn(number: String) {
        presenterScope.launch(Dispatchers.IO) { userRepository.signInPhone(number, callbacks) }
    }

    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                viewState.showToast(VER_FAILED)
            }

            override fun onCodeSent(
                verifId: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                verificationId = verifId
                resendToken = forceResendingToken
                viewState.showToast(CODE_SEND)
            }
        }

    companion object {
        private const val CODE_SEND = "Code send"
        private const val AUTH_FAILED = "Authentication failed"
        private const val AUTH_SUCCESS = "Authentication success"
        private const val VER_FAILED = "Verification Failed"
    }
}
