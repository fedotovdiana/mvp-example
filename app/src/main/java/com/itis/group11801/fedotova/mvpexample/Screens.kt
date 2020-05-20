package com.itis.group11801.fedotova.mvpexample

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.itis.group11801.fedotova.mvpexample.ui.game.GameActivity
import com.itis.group11801.fedotova.mvpexample.ui.main.MainActivity
import com.itis.group11801.fedotova.mvpexample.ui.main.info.InfoFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.signin_email.EmailSignInFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.signin_phone.PhoneSignInFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.signup.SignUpFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.start_signin_google.StartFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object StartScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = StartFragment()
    }

    object InfoScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = InfoFragment()
    }

    object EmailSignInScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = EmailSignInFragment()
    }

    object PhoneSignInScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = PhoneSignInFragment()
    }

    object SignUpScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = SignUpFragment()
    }

    object MainActivityScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent =
            Intent(context, MainActivity::class.java)
    }

    object GameScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent? =
            Intent(context, GameActivity::class.java)
    }
}
