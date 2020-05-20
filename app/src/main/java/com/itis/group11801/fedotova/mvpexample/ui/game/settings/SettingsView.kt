package com.itis.group11801.fedotova.mvpexample.ui.game.settings

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SettingsView : MvpView {
    fun navigateToAction()
}

