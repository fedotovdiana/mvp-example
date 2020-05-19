package com.itis.group11801.fedotova.mvpexample.ui.signin_google_start

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface StartView : MvpView {

    fun navigateToInfo()

    fun showToast(msg: String)

    fun signIn()

    fun navigateToEmail()

    fun navigateToPhone()
}
