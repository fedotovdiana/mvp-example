package com.itis.group11801.fedotova.mvpexample.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.group11801.fedotova.mvpexample.App
import com.itis.group11801.fedotova.mvpexample.NavigatorMain
import com.itis.group11801.fedotova.mvpexample.R
import com.itis.group11801.fedotova.mvpexample.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = NavigatorMain(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.injectMainActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
        router.replaceScreen(Screens.StartScreen)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
