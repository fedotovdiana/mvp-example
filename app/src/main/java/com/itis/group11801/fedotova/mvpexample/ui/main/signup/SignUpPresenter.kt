package com.itis.group11801.fedotova.mvpexample.ui.main.signup

import com.itis.group11801.fedotova.mvpexample.Screens
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    private val repository: UserRepository,
    private val router: Router
) : MvpPresenter<SignUpView>() {

    fun signUp(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            presenterScope.launch {
                if (withContext(Dispatchers.IO) {
                        repository.signUpEmailAndPassword(email, password)
                    }) {
                    viewState.showToast(TOAST_INFO)
                    router.navigateTo(Screens.EmailSignInScreen)
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
