package com.itis.group11801.fedotova.mvpexample.ui.main.start_signin_google

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface StartView : MvpView {

    fun showToast(msg: String)

    fun signIn()
}
