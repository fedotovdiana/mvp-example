package com.itis.group11801.fedotova.mvpexample.di

import android.app.Application
import com.itis.group11801.fedotova.mvpexample.di.modules.DataModule
import com.itis.group11801.fedotova.mvpexample.di.modules.FirebaseModule
import com.itis.group11801.fedotova.mvpexample.di.modules.NavigationModule
import com.itis.group11801.fedotova.mvpexample.ui.game.action.ActionFragment
import com.itis.group11801.fedotova.mvpexample.ui.game.game.GameFragment
import com.itis.group11801.fedotova.mvpexample.ui.game.home.HomeFragment
import com.itis.group11801.fedotova.mvpexample.ui.game.score.ScoreFragment
import com.itis.group11801.fedotova.mvpexample.ui.game.settings.SettingsFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.MainActivity
import com.itis.group11801.fedotova.mvpexample.ui.main.info.InfoFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.signin_email.EmailSignInFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.signin_phone.PhoneSignInFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.signup.SignUpFragment
import com.itis.group11801.fedotova.mvpexample.ui.main.start_signin_google.StartFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FirebaseModule::class, DataModule::class, NavigationModule::class])
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
    fun injectSignUpFragment(signUpFragment: SignUpFragment)
    fun injectStartFragment(startFragment: StartFragment)
    fun injectMainActivity(activity: MainActivity)
    fun injectSettingsFragment(settingsFragment: SettingsFragment)
    fun injectHomeFragment(homeFragment: HomeFragment)
    fun injectScoreFragment(scoreFragment: ScoreFragment)
    fun injectActionFragment(actionFragment: ActionFragment)
    fun injectGameFragment(gameFragment: GameFragment)
}
