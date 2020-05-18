package com.itis.group11801.fedotova.mvpexample.ui.signin_phone

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface PhoneSignInView : MvpView {

    fun navigateToInfo()

    fun showToast(msg: String)
}
