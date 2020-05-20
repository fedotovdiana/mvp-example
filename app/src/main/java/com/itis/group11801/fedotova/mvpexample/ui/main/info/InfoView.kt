package com.itis.group11801.fedotova.mvpexample.ui.main.info

import com.itis.group11801.fedotova.mvpexample.data.model.Info
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface InfoView : MvpView {

    fun showToast(msg: String)

    fun updateList(list: MutableList<Info>)

    fun inflateAddDialog()
}
