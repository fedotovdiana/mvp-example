package com.itis.group11801.fedotova.mvpexample.ui.game.home

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface HomeView : MvpView {
    fun navigateToMain()
}
