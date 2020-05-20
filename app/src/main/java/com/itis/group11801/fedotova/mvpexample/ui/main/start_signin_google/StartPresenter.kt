package com.itis.group11801.fedotova.mvpexample.ui.main.start_signin_google

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.itis.group11801.fedotova.mvpexample.Screens
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import moxy.presenterScope
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class StartPresenter @Inject constructor(
    private var userRepository: UserRepository,
    private var router: Router
) : MvpPresenter<StartView>() {

    private var user: FirebaseUser? = userRepository.getCurrentUser()

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        presenterScope.launch {
            if (withContext(Dispatchers.IO) { userRepository.signInWithCredential(credential) }) {
                router.navigateTo(Screens.InfoScreen)
            } else {
                viewState.showToast(AUTH_FAILED)
            }
        }
    }

    fun signIn() {
        viewState.signIn()
    }

    fun navigateToInfo() {
        user?.let { router.navigateTo(Screens.InfoScreen) }
    }

    fun navigateToEmail() {
        user ?: router.navigateTo(Screens.EmailSignInScreen)
    }

    fun navigateToPhone() {
        user ?: router.navigateTo(Screens.PhoneSignInScreen)
    }

    fun onException() {
        viewState.showToast(GOOGLE_AUTH_FAILED)
    }

    companion object {
        private const val AUTH_FAILED = "Authentication failed"
        private const val GOOGLE_AUTH_FAILED = "Google sign in failed"
    }
}
