package com.itis.group11801.fedotova.mvpexample.ui.signin_google_start

import com.google.firebase.auth.GoogleAuthProvider
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class StartPresenter @Inject constructor(
    private var repository: UserRepository
) : MvpPresenter<StartView>() {

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        presenterScope.launch {
            if (withContext(Dispatchers.IO) { repository.signInWithCredential(credential) }) {
                viewState.navigateToInfo()
            } else {
                viewState.showToast(AUTH_FAILED)
            }
        }
    }

    fun signIn() {
        viewState.signIn()
    }

    fun navigateToInfo() {
        viewState.navigateToInfo()
    }

    fun navigateToEmail() {
        viewState.navigateToEmail()
    }

    fun navigateToPhone() {
        viewState.navigateToPhone()
    }

    fun configureGoogleClient() {
        viewState.configureGoogleClient()
    }

    fun onException() {
        viewState.showToast(GOOGLE_AUTH_FAILED)
    }

    companion object {
        private const val AUTH_FAILED = "Authentication failed"
        private const val GOOGLE_AUTH_FAILED = "Google sign in failed"
    }
}
