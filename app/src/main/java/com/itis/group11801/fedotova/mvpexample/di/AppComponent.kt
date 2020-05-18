package com.itis.group11801.fedotova.mvpexample.di

import android.app.Application
import com.itis.group11801.fedotova.mvpexample.GoogleSignInFragment
import com.itis.group11801.fedotova.mvpexample.di.modules.DataModule
import com.itis.group11801.fedotova.mvpexample.di.modules.FirebaseModule
import com.itis.group11801.fedotova.mvpexample.ui.info.InfoFragment
import com.itis.group11801.fedotova.mvpexample.ui.signin_email.EmailSignInFragment
import com.itis.group11801.fedotova.mvpexample.ui.signin_google_start.StartFragment
import com.itis.group11801.fedotova.mvpexample.ui.signin_phone.PhoneSignInFragment
import com.itis.group11801.fedotova.mvpexample.ui.signup.SignUpFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FirebaseModule::class, DataModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun injectInfoFragment(infoFragment: InfoFragment)
    fun injectEmailSignInFragment(emailSignInFragment: EmailSignInFragment)
    fun injectPhoneSignInFragment(phoneSignInFragment: PhoneSignInFragment)
    fun injectGoogleSignInFragment(googleSignInFragment: GoogleSignInFragment)
    fun injectSignUpFragment(signUpFragment: SignUpFragment)
    fun injectStartFragment(startFragment: StartFragment)
}
