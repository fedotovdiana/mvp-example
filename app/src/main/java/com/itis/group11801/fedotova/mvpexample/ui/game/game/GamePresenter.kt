package com.itis.group11801.fedotova.mvpexample.ui.game.game

import moxy.MvpPresenter
import javax.inject.Inject

class GamePresenter @Inject constructor() : MvpPresenter<GameView>() {
    fun startGame() {
        viewState.navigateToSettings()
    }
}
