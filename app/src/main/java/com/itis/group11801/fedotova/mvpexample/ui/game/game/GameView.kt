package com.itis.group11801.fedotova.mvpexample.ui.game.game

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface GameView : MvpView {
    fun navigateToSettings()
}
