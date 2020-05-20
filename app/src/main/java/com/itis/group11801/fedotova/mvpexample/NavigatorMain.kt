package com.itis.group11801.fedotova.mvpexample

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import com.itis.group11801.fedotova.mvpexample.Screens.GameScreen.getActivityIntent
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class NavigatorMain(
    private val activity: AppCompatActivity
) : Navigator {

    override fun applyCommands(commands: Array<out Command>) {
        for (command in commands) applyCommand(command)
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> forward(command)
            is Replace -> replace(command)
        }
    }

    private fun replace(command: Replace) {
        val navController = activity.findNavController(R.id.navFragment)
        when (command.screen as SupportAppScreen) {
            is Screens.StartScreen -> navController.navigate(R.id.nav_start)
            is Screens.InfoScreen -> navController.navigate(R.id.nav_info)
            is Screens.SignUpScreen -> navController.navigate(R.id.nav_sign_up)
            is Screens.EmailSignInScreen -> navController.navigate(R.id.nav_email_sign_in)
            is Screens.PhoneSignInScreen -> navController.navigate(R.id.nav_phone_sign_in)
            is Screens.GameScreen -> {
                startActivity(activity, getActivityIntent(activity)!!, null)
            }
        }
    }

    private fun forward(command: Forward) {
        val navController = activity.findNavController(R.id.navFragment)
        when (command.screen as SupportAppScreen) {
            is Screens.StartScreen -> navController.navigate(R.id.nav_start)
            is Screens.InfoScreen -> navController.navigate(R.id.nav_info)
            is Screens.SignUpScreen -> navController.navigate(R.id.nav_sign_up)
            is Screens.EmailSignInScreen -> navController.navigate(R.id.nav_email_sign_in)
            is Screens.PhoneSignInScreen -> navController.navigate(R.id.nav_phone_sign_in)
            is Screens.GameScreen -> {
                startActivity(activity, getActivityIntent(activity)!!, null)
            }
        }
    }
}