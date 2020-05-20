package com.itis.group11801.fedotova.mvpexample.ui.main.signup

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SignUpView : MvpView {

    fun showToast(msg: String)
}
