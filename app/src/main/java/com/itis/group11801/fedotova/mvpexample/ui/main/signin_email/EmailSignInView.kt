package com.itis.group11801.fedotova.mvpexample.ui.main.signin_email

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface EmailSignInView : MvpView {

    fun showToast(msg: String)
}
