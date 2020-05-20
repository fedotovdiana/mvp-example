package com.itis.group11801.fedotova.mvpexample.ui.game.home

import moxy.MvpPresenter
import javax.inject.Inject

class HomePresenter @Inject constructor() : MvpPresenter<HomeView>() {

    fun exit() {
        viewState.navigateToMain()
    }
}
