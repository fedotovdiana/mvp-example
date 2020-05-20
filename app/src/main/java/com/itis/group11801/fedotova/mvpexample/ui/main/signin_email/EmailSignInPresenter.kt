package com.itis.group11801.fedotova.mvpexample.ui.main.signin_email

import com.itis.group11801.fedotova.mvpexample.Screens
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class EmailSignInPresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val router: Router
) : MvpPresenter<EmailSignInView>() {

    fun signIn(email: String, password: String) {
        presenterScope.launch {
            if (withContext(Dispatchers.IO) { userRepository.signInEmail(email, password) }) {
                viewState.showToast(AUTH_SUCCESS)
                router.navigateTo(Screens.InfoScreen)
            } else {
                viewState.showToast(AUTH_FAILED)
            }
        }
    }

    fun resetPassword(email: String) {
        if (email.isNotEmpty()) {
            presenterScope.launch {
                if (withContext(Dispatchers.IO) { userRepository.resetPassword(email) }) {
                    viewState.showToast(SEND_SUCCESS)
                } else {
                    viewState.showToast(SEND_FAILED)
                }
            }
        } else {
            viewState.showToast(INCORRECT_EMAIL)
        }
    }

    fun navigateToSignUp() {
        router.navigateTo(Screens.SignUpScreen)
    }

    companion object {
        private const val AUTH_SUCCESS = "Authentication success"
        private const val AUTH_FAILED = "Authentication failed"
        private const val SEND_SUCCESS = "Check email to reset password"
        private const val SEND_FAILED = "Failed"
        private const val INCORRECT_EMAIL = "Incorrect email"
    }
}