package com.itis.group11801.fedotova.mvpexample.ui.signup

import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    private val repository: UserRepository
) : MvpPresenter<SignUpView>() {

    fun signUp(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            presenterScope.launch {
                if (withContext(Dispatchers.IO) {
                        repository.signUpEmailAndPassword(email, password)
                    }) {
                    viewState.showToast(TOAST_INFO)
                    viewState.navigateToSignIn()
                } else {
                    viewState.showToast(AUTH_FAILED)
                }
            }
        }
    }

    companion object {
        private const val AUTH_FAILED = "Authentication Failed"
        private const val TOAST_INFO = "Success sign up"
    }
}
