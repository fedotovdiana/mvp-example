package com.itis.group11801.fedotova.mvpexample.ui.signin_email

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface EmailSignInView : MvpView {

    fun navigateToSignUp()

    fun navigateToInfo()

    fun showToast(msg: String)
}