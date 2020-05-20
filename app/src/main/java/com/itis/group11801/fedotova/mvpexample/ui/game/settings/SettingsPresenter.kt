package com.itis.group11801.fedotova.mvpexample.ui.game.settings

import moxy.MvpPresenter
import javax.inject.Inject

class SettingsPresenter @Inject constructor() : MvpPresenter<SettingsView>() {

    fun start() {
        viewState.navigateToAction()
    }
}
